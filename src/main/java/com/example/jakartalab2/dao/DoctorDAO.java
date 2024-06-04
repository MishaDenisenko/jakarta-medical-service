package com.example.jakartalab2.dao;

import com.example.jakartalab2.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorDAO {
    public final List<Doctor> store = new ArrayList<>();

    public Doctor getById(int id) {
        return store.stream().filter(doctor -> doctor.getId() == id).findFirst().orElse(null);
    }

    public List<Doctor> getByProfession(Doctor.PROFESSION profession){
        return store.stream().filter(doctor -> doctor.getProfession() == profession).collect(Collectors.toList());
    }

    public boolean addDoctor(Doctor doctor){
        boolean isExist = store.stream().anyMatch(d -> d.getId() == doctor.getId());

        if (isExist) return false;

        return store.add(doctor);
    }

    public Doctor.PROFESSION getProfessionById(int id){
        Doctor doctor = store
            .stream()
            .filter(d -> d.getId() == id)
            .findFirst()
            .orElse(null);

        if (doctor == null) return Doctor.PROFESSION.UNKNOWN;
        return doctor.getProfession();
    }

    public boolean isDoctorExist(int id){
        return store.stream().anyMatch(d -> d.getId() == id);
    }

    public List<Doctor> getDoctors(){
        return store;
    }

    public boolean removeDoctor(Doctor doctor){
        boolean isExist = store.stream().anyMatch(d -> d.getId() == doctor.getId());

        if (!isExist) return false;

        return store.remove(doctor);
    }
}
