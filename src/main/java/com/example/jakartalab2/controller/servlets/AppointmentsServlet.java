package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.ejb.DoPostHandler;
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

@WebServlet("/my-appointments")
public class AppointmentsServlet extends HttpServlet {
    @EJB
    DoPostHandler postHandler;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = (int) req.getSession().getAttribute("id");

        postHandler.setUser(id);

        final User user = postHandler.getUser();
        final List<ReceiptTime> times = user.getReceiptTimeList();

        req.setAttribute("user", user);
        req.setAttribute("userTimes", times.isEmpty() ? null : times);

        req.getRequestDispatcher("/WEB-INF/view/appointments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String timeId = req.getParameter("timeId");
        final String action = req.getParameter("action");

        postHandler.doPostUser(timeId, action);

        doGet(req, resp);
    }
}
