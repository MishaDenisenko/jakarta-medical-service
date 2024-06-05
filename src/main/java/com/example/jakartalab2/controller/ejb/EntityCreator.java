package com.example.jakartalab2.controller.ejb;

import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

import static com.example.jakartalab2.model.Doctor.PROFESSION.DENTIST;
import static com.example.jakartalab2.model.Doctor.PROFESSION.SURGEON;
import static com.example.jakartalab2.model.User.ROLE.ADMIN;
import static com.example.jakartalab2.model.User.ROLE.USER;

@Stateless
public class EntityCreator {
    @EJB
    IdCreator creator;

    public List<User> createUsers(){
        List<User> users = new ArrayList<>();

        users.add(new User(creator.getUniqId(), "mykhailo", "1111", ADMIN));
        users.add(new User(creator.getUniqId(), "ranalda", "2222", USER));
        users.add(new User(creator.getUniqId(), "messi", "3333", USER));

        return users;
    }

    public List<Doctor> createDoctors(){
        List<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(creator.getUniqId(), "Dr. QWERTY", SURGEON));
        doctors.add(new Doctor(creator.getUniqId(), "Dr. WASD", DENTIST));

        return doctors;
    }
}
