package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.utils.DoPostWithRole;
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

import static com.example.jakartalab2.controller.utils.DoPostWithRole.PAGE.APPOINTMENTS;

@WebServlet("/my-appointments")
public class AppointmentsServlet extends HttpServlet {
    private DoPostWithRole dp;
    @Override
    public void init() throws ServletException {
        dp = new DoPostWithRole(APPOINTMENTS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = (int) req.getSession().getAttribute("id");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDAO");
        final User user = dao.get().getById(id);
        final List<ReceiptTime> times = user.getReceiptTimeList();

        req.setAttribute("user", user);
        req.setAttribute("userTimes", times.isEmpty() ? null : times);

        req.getRequestDispatcher("/WEB-INF/view/appointments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dp.doPostWithRole(req, new Doctor());

        doGet(req, resp);
    }
}
