package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.jakartalab2.model.Doctor.PROFESSION.SURGEON;
import static com.example.jakartalab2.model.Doctor.PROFESSION.DENTIST;
import static com.example.jakartalab2.model.User.ROLE.ADMIN;
import static com.example.jakartalab2.model.User.ROLE.USER;

public class Helper {
    private static int uniqId = 10;

    public synchronized static int getUniqId(){
        return uniqId++;
    }

    public static List<User> createUsers(){
        List<User> users = new ArrayList<>();

        users.add(new User(getUniqId(), "mykhailo", "1111", ADMIN));
        users.add(new User(getUniqId(), "ranalda", "2222", USER));
        users.add(new User(getUniqId(), "messi", "3333", USER));

        return users;
    }

    public static List<Doctor> createDoctors(){
        List<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(getUniqId(), "Dr. QWERTY", SURGEON));
        doctors.add(new Doctor(getUniqId(), "Dr. WASD", DENTIST));

        return doctors;
    }
}
