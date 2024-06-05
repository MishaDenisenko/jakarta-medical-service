package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.controller.ejb.DAO;
import com.example.jakartalab2.controller.ejb.EntityCreator;
import com.example.jakartalab2.dao.DoctorDAO;
import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> userDAO;
    private AtomicReference<DoctorDAO> doctorDAO;

    @EJB
    EntityCreator creator;
    @EJB
    DAO dao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDAO = new AtomicReference<>(new UserDAO());
        dao.setUserDAO(userDAO);

        List<User> users = creator.createUsers();
        users.forEach(user -> userDAO.get().addUser(user));

        doctorDAO = new AtomicReference<>(new DoctorDAO());
        dao.setDoctorDAO(doctorDAO);

        List<Doctor> doctors = creator.createDoctors();
        doctors.forEach(doctor -> doctorDAO.get().addDoctor(doctor));

        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("doctorDAO", doctorDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao.setUserDAO(null);
        dao.setDoctorDAO(null);
    }
}
