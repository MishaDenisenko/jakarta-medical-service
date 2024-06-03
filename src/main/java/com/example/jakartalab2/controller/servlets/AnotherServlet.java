package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.controller.utils.AuthFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/another")
public class AnotherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("role") == null || req.getSession().getAttribute("role") == "UNKNOWN"){
//            resp.sendRedirect("/auth");
//            return;
//        }

        req.getRequestDispatcher("/WEB-INF/view/another.jsp").forward(req, resp);
    }

}
