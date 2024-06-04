package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.utils.DoPostWithRole;
import com.example.jakartalab2.controller.utils.DoctorHelper;
import com.example.jakartalab2.dao.DoctorDAO;
import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
import com.example.jakartalab2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.jakartalab2.controller.utils.DoPostWithRole.PAGE.DOCTORS;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {
    private DoctorHelper dh;
    private DoPostWithRole dp;

    @Override
    public void init() throws ServletException {
        dh = new DoctorHelper();
        dp = new DoPostWithRole(DOCTORS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String doctorId = req.getParameter("id");

        @SuppressWarnings("unchecked")
        final AtomicReference<DoctorDAO> dao = (AtomicReference<DoctorDAO>) req.getServletContext().getAttribute("doctorDAO");
        final Doctor doctor = dao.get().getById(Integer.parseInt(doctorId));
        final List<ReceiptTime> doctorTimes = doctor.getReceiptTimeList();

        req.setAttribute("doctor", doctor);
        req.setAttribute("doctorTimes", doctorTimes.isEmpty() ? null : doctorTimes);

        req.getRequestDispatcher("/WEB-INF/view/doctor_info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        final AtomicReference<DoctorDAO> dao = (AtomicReference<DoctorDAO>) req.getServletContext().getAttribute("doctorDAO");
        final String doctorId = req.getParameter("id");

        final Doctor doctor = dao.get().getById(Integer.parseInt(doctorId));

        dp.doPostWithRole(req, doctor);

//        final User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
//
//        if (role == User.ROLE.USER) userDoPost(req, resp, doctor);
//        else if (role == User.ROLE.ADMIN) adminDoPost(req, resp, doctor);

        doGet(req, resp);
    }

    private void userDoPost(HttpServletRequest req, HttpServletResponse resp, Doctor doctor){
        int id = (int) req.getSession().getAttribute("id");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDAO");
        final User user = dao.get().getById(id);

        String delParameter =  req.getParameter("Delete");
        String addParameter =  req.getParameter("Add");

        if (delParameter != null){
            final int timeId = dh.getIdFromParam(delParameter);
            final ReceiptTime time = user.getTimeById(timeId);

            if (time.getUser() != null) time.getUser().removeReceiptTime(time);

            user.removeReceiptTime(time);
        }
        else if (addParameter != null){
            final int timeId = dh.getIdFromParam(addParameter);
            final String hour = req.getParameter("hour");
            final String minutes = req.getParameter("minutes");

            if (!hour.isEmpty() && !minutes.isEmpty()){
                user.addReceiptTime(doctor.getTimeById(timeId));
            }
        }
    }

    private void adminDoPost(HttpServletRequest req, HttpServletResponse resp, Doctor doctor) throws IOException {
        String delParameter =  req.getParameter("Delete");
        String changeParameter =  req.getParameter("Change");
        String addParameter =  req.getParameter("Add");

        if (delParameter != null){
            final int id = dh.getIdFromParam(delParameter);
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
                    new ReceiptTime(dh.getUniqId(), Integer.parseInt(hour), Integer.parseInt(minutes), doctor)
                );
            }
        }
        else if (changeParameter != null){
            final int id = dh.getIdFromParam(changeParameter);
            final ReceiptTime time = doctor.getTimeById(id);

            final String hour = req.getParameter("hour_" + id);
            final String minutes = req.getParameter("minutes_" + id);

            if (!hour.isEmpty() && !minutes.isEmpty()){
                time.setHour(Integer.parseInt(hour));
                time.setMinutes(Integer.parseInt(minutes));
            }
        }
    }


}
