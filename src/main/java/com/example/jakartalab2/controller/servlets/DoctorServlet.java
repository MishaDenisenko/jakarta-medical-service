package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.utils.DoPostWithRole;
import com.example.jakartalab2.dao.DoctorDAO;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
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
    private DoPostWithRole dp;

    @Override
    public void init() throws ServletException {
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

        doGet(req, resp);
    }
}
