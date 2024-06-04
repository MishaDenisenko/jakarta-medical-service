package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.dao.DoctorDAO;
import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
import com.example.jakartalab2.model.User;
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
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDAO = new AtomicReference<>(new UserDAO());

        List<User> users = Helper.createUsers();
        users.forEach(user -> userDAO.get().addUser(user));

        doctorDAO = new AtomicReference<>(new DoctorDAO());

        List<Doctor> doctors = Helper.createDoctors();
        doctors.forEach(doctor -> doctorDAO.get().addDoctor(doctor));

        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("doctorDAO", doctorDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        userDAO = null;
        doctorDAO = null;
    }
}
