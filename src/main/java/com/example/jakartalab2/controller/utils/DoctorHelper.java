package com.example.jakartalab2.controller.utils;

import java.util.UUID;

public class DoctorHelper {
    private int uniqId = 10;
    public int getIdFromParam(String parameter){
        return Integer.parseInt(parameter.split("_")[1]);
    }

    public int getUniqId(){
        return nextId();
    }

    private synchronized int nextId(){
        return uniqId++;
    }
}
