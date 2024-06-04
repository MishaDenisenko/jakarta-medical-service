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

import static com.example.jakartalab2.model.Doctor.PROFESSION.PROFESSION1;
import static com.example.jakartalab2.model.Doctor.PROFESSION.PROFESSION2;
import static com.example.jakartalab2.model.User.ROLE.ADMIN;
import static com.example.jakartalab2.model.User.ROLE.USER;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> userDAO;
    private AtomicReference<DoctorDAO> doctorDAO;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDAO = new AtomicReference<>(new UserDAO());

        List<User> users = Helper.createUsers();
        users.forEach(user -> userDAO.get().addUser(user));

//        userDAO.get().addUser(new User(1, "mykhailo", "1111", ADMIN));
//        userDAO.get().addUser(new User(2, "ranalda", "2222", USER));

        doctorDAO = new AtomicReference<>(new DoctorDAO());

        List<Doctor> doctors = Helper.createDoctors();
        doctors.forEach(doctor -> doctorDAO.get().addDoctor(doctor));

//        doctorDAO.get().addDoctor(new Doctor(1, "Dr. Q", PROFESSION1));
//        doctorDAO.get().addDoctor(new Doctor(2, "Dr. W", PROFESSION2));

//        ReceiptTime time = new ReceiptTime(1, 2, 20, doctorDAO.get().getById(1));
//        doctorDAO.get().getById(1).addReceiptTime(time);

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
