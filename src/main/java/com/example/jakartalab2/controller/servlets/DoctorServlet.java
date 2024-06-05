package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.ejb.DoPostHandler;
import com.example.jakartalab2.model.Doctor;
import com.example.jakartalab2.model.ReceiptTime;
import com.example.jakartalab2.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {
    @EJB
    DoPostHandler postHandler;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String doctorId = req.getParameter("id");

        postHandler.setDoctor(Integer.parseInt(doctorId));
        postHandler.setUser((int) req.getSession().getAttribute("id"));

        final Doctor doctor = postHandler.getDoctor();
        final List<ReceiptTime> doctorTimes = doctor.getReceiptTimeList();

        req.setAttribute("doctor", doctor);
        req.setAttribute("doctorTimes", doctorTimes.isEmpty() ? null : doctorTimes);

        req.getRequestDispatcher("/WEB-INF/view/doctor_info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");

        final String timeId = req.getParameter("timeId");
        final String action = req.getParameter("action");
        final String hour = req.getParameter(action.equals("change") ? "hour_" + timeId : "hour");
        final String minutes = req.getParameter(action.equals("change") ? "minutes_" + timeId : "minutes");

        if (role == User.ROLE.ADMIN) postHandler.doPostAdmin(timeId, action, hour, minutes);
        else if (role == User.ROLE.USER) postHandler.doPostUser(timeId, action);


        doGet(req, resp);
    }
}
