package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.dao.DoctorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        final AtomicReference<DoctorDAO> dao = (AtomicReference<DoctorDAO>) req.getServletContext().getAttribute("doctorDAO");

        req.setAttribute("doctorList", dao.get().getDoctors());
        req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
    }
}
