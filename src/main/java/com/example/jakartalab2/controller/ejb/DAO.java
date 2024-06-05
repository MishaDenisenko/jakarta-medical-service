package com.example.jakartalab2.controller.ejb;

import com.example.jakartalab2.dao.DoctorDAO;
import com.example.jakartalab2.dao.UserDAO;
import jakarta.ejb.Singleton;

import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class DAO {
    private AtomicReference<UserDAO> userDAO;
    private AtomicReference<DoctorDAO> doctorDAO;

    public AtomicReference<UserDAO> getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(AtomicReference<UserDAO> userDAO) {
        this.userDAO = userDAO;
    }

    public AtomicReference<DoctorDAO> getDoctorDAO() {
        return doctorDAO;
    }

    public void setDoctorDAO(AtomicReference<DoctorDAO> doctorDAO) {
        this.doctorDAO = doctorDAO;
    }
}
