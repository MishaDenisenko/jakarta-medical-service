package com.example.jakartalab2.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name;
    private Doctor.PROFESSION profession;
    private final List<ReceiptTime> receiptTimeList = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(int id, String name, Doctor.PROFESSION profession) {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PROFESSION getProfession() {
        return profession;
    }

    public void setProfession(PROFESSION profession) {
        this.profession = profession;
    }

    public List<ReceiptTime> getReceiptTimeList() {
        return receiptTimeList;
    }

    public boolean addReceiptTime(ReceiptTime time){
        if (receiptTimeList.isEmpty()) return receiptTimeList.add(time);

        boolean isExist = receiptTimeList.stream().anyMatch(t -> t.getHour() == time.getHour() && t.getMinutes() == time.getMinutes());
        if (isExist) return false;

        return  receiptTimeList.add(time);
    }

    public boolean removeReceiptTime(ReceiptTime time){
        boolean isExist = receiptTimeList.stream().anyMatch(t -> t.getId() == time.getId());

        if (isExist) receiptTimeList.remove(time);
        return false;
    }

    public ReceiptTime getTimeById(int id){
        return receiptTimeList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public enum PROFESSION {
        SURGEON, DENTIST, UNKNOWN
    }
}
