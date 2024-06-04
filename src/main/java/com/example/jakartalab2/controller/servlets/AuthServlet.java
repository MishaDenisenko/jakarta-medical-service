package com.example.jakartalab2.controller.servlets;

import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.jakartalab2.model.User.ROLE.UNKNOWN;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDAO");

        if (dao.get().isUserExist(login, password)){
            final User user = dao.get().getUserByLoginPassword(login, password);

            req.getSession().setAttribute("id",  user.getId());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("password", user.getPassword());
            req.getSession().setAttribute("role", user.getRole());

            doRedirect(req, resp, user.getRole());
        }
        else doRedirect(req, resp, UNKNOWN);
    }

    private void doRedirect(final HttpServletRequest req, final HttpServletResponse res, final User.ROLE role) throws ServletException, IOException {
        if (role == UNKNOWN) req.getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, res);
        else res.sendRedirect("/home");
    }
}
