package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.concurrent.atomic.AtomicReference;

import static com.example.jakartalab2.model.User.ROLE.ADMIN;
import static com.example.jakartalab2.model.User.ROLE.USER;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> dao;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dao = new AtomicReference<>(new UserDAO());

        dao.get().addUser(new User(1, "mykhailo", "1111", ADMIN));
        dao.get().addUser(new User(1, "ranalda", "2222", USER));

        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
