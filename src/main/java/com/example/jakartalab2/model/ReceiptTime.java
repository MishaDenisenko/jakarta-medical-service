package com.example.jakartalab2.model;

import static com.example.jakartalab2.model.User.ROLE.USER;

public class ReceiptTime {
    private int id;
    private int hour;
    private int minutes;
    private STATUS status;
    private User user;
    private Doctor doctor;

    public ReceiptTime(int id, int hour, int minutes, Doctor doctor) {
        this.id = id;
        this.hour = hour;
        this.minutes = minutes;
        this.status = STATUS.FREE;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public String getTime(){
        return hour + ":" + minutes;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null) this.user = null;
        else if (user.getRole() == USER) this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public enum STATUS {
        BUSY, FREE
    }
}
