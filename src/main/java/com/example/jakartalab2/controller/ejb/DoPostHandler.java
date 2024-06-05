package com.example.jakartalab2.controller.ejb;

import com.example.jakartalab2.controller.utils.Helper;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
import com.example.jakartalab2.model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

@Stateful
public class DoPostHandler {
    private Doctor doctor;
    private User user;

    @EJB
    private DAO dao;
    @EJB
    private IdCreator idCreator;

    public void doPostUser(String timeId, String action){

        switch (action){
            case "delete": {
                final ReceiptTime time = user.getTimeById(Integer.parseInt(timeId));

                if (time.getUser() != null) time.getUser().removeReceiptTime(time);
                user.removeReceiptTime(time);

                break;
            }
            case "add": {
                user.addReceiptTime(doctor.getTimeById(Integer.parseInt(timeId)));

                break;
            }
        }
    }

    public void doPostAdmin(String timeId, String action, String hour, String minutes){

        switch (action) {
            case "delete": {
                final ReceiptTime time = doctor.getTimeById(Integer.parseInt(timeId));

                if (time.getUser() != null) time.getUser().removeReceiptTime(time);

                time.setDoctor(null);
                doctor.removeReceiptTime(time);

                break;
            }
            case "add": {
                if (hour.isEmpty() || minutes.isEmpty()) break;

                doctor.addReceiptTime(
                    new ReceiptTime(idCreator.getUniqId(), Integer.parseInt(hour), Integer.parseInt(minutes), doctor)
                );

                break;
            }
            case "change": {
                final ReceiptTime time = doctor.getTimeById(Integer.parseInt(timeId));

                if (hour.isEmpty() || minutes.isEmpty()) break;

                time.setHour(Integer.parseInt(hour));
                time.setMinutes(Integer.parseInt(minutes));

                break;
            }
        }
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(int id) {
        if (id <= 0) this.doctor = null;
        else this.doctor = dao.getDoctorDAO().get().getById(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(int id) {
        if (id <= 0) this.user = null;
        else this.user = dao.getUserDAO().get().getById(id);
    }
}
