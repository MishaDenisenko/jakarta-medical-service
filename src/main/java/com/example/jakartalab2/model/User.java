package com.example.jakartalab2.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private ROLE role;

    private final List<ReceiptTime> receiptTimeList = new ArrayList<>();


    public User(int id, String login, String password, ROLE role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public List<ReceiptTime> getReceiptTimeList() {
        return receiptTimeList;
    }

    public ReceiptTime getTimeById(int id){
        return receiptTimeList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public boolean addReceiptTime(ReceiptTime time){
        if (receiptTimeList.isEmpty()) {
            time.setUser(this);
            time.setStatus(ReceiptTime.STATUS.BUSY);
            return receiptTimeList.add(time);
        }

        boolean isExist = receiptTimeList.stream().anyMatch(t -> t.getId() == time.getId());
        if (isExist) return false;

        time.setUser(this);
        time.setStatus(ReceiptTime.STATUS.BUSY);

        return  receiptTimeList.add(time);
    }

    public boolean removeReceiptTime(ReceiptTime time){
        boolean isExist = receiptTimeList.stream().anyMatch(t -> t.getId() == time.getId());
        if (isExist) {
            time.setUser(null);
            time.setStatus(ReceiptTime.STATUS.FREE);
            receiptTimeList.remove(time);
        }

        return false;
    }

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
