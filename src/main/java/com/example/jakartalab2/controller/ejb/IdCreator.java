package com.example.jakartalab2.controller.ejb;

import jakarta.ejb.Singleton;

@Singleton
public class IdCreator {
    private int id;

    public int getUniqId(){
        return id++;
    }
}
