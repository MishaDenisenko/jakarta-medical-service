package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
import com.example.jakartalab2.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.atomic.AtomicReference;

import static com.example.jakartalab2.model.User.ROLE.ADMIN;
import static com.example.jakartalab2.model.User.ROLE.USER;

public class DoPostWithRole {

    private PAGE page;

    public DoPostWithRole(PAGE page) {
        this.page = page;
    }

    public void doPostWithRole(HttpServletRequest req, Doctor doctor){
        final User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role == USER) doPostUser(req, doctor);
        else if (role == ADMIN) doPostAdmin(req, doctor);
    }

    private void doPostUser(HttpServletRequest req, Doctor doctor){
        if (page == PAGE.DOCTORS) userOnDoctors(req, doctor);
        else if (page == PAGE.APPOINTMENTS) userOnAppointment(req);
    }

    private void userOnDoctors(HttpServletRequest req, Doctor doctor){
        int id = (int) req.getSession().getAttribute("id");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDAO");
        final User user = dao.get().getById(id);

        String delParameter =  req.getParameter("Delete");
        String addParameter =  req.getParameter("Add");

        if (delParameter != null){
            final int timeId = Integer.parseInt(req.getParameter("timeId"));
            final ReceiptTime time = user.getTimeById(timeId);

            if (time.getUser() != null) time.getUser().removeReceiptTime(time);

            user.removeReceiptTime(time);
        }
        else if (addParameter != null){
            final int timeId = Integer.parseInt(req.getParameter("timeId"));
            final String hour = req.getParameter("hour");
            final String minutes = req.getParameter("minutes");

            if (!hour.isEmpty() && !minutes.isEmpty()){
                user.addReceiptTime(doctor.getTimeById(timeId));
            }
        }
    }
    private void userOnAppointment(HttpServletRequest req){
        final int id = (int) req.getSession().getAttribute("id");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDAO");
        final User user = dao.get().getById(id);

        String delParameter =  req.getParameter("Delete");

        if (delParameter != null){
            final int timeId = Integer.parseInt(req.getParameter("timeId"));
            final ReceiptTime time = user.getTimeById(timeId);

            time.setUser(null);

            user.removeReceiptTime(time);
        }
    }
    private void doPostAdmin(HttpServletRequest req, Doctor doctor){
        String delParameter =  req.getParameter("Delete");
        String changeParameter =  req.getParameter("Change");
        String addParameter =  req.getParameter("Add");

        if (delParameter != null){
            final int id = Integer.parseInt(req.getParameter("timeId"));
            final ReceiptTime time = doctor.getTimeById(id);

            if (time.getUser() != null) time.getUser().removeReceiptTime(time);

            time.setDoctor(null);
            doctor.removeReceiptTime(time);
        }
        else if (addParameter != null){
            final String hour = req.getParameter("hour");
            final String minutes = req.getParameter("minutes");

            if (!hour.isEmpty() && !minutes.isEmpty()){
                doctor.addReceiptTime(
                    new ReceiptTime(Helper.getUniqId(), Integer.parseInt(hour), Integer.parseInt(minutes), doctor)
                );
            }
        }
        else if (changeParameter != null){
            final int id = Integer.parseInt(req.getParameter("timeId"));
            final ReceiptTime time = doctor.getTimeById(id);

            final String hour = req.getParameter("hour_" + id);
            final String minutes = req.getParameter("minutes_" + id);

            if (!hour.isEmpty() && !minutes.isEmpty()){
                time.setHour(Integer.parseInt(hour));
                time.setMinutes(Integer.parseInt(minutes));
            }
        }
    }

    public enum PAGE {
        DOCTORS, APPOINTMENTS
    }
}
