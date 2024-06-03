package com.example.jakartalab2.controller.utils;

import com.example.jakartalab2.dao.UserDAO;
import com.example.jakartalab2.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.jakartalab2.model.User.ROLE.UNKNOWN;
import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = {"/"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) request.getServletContext().getAttribute("dao");
        final HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))){
            User.ROLE role = (User.ROLE) session.getAttribute("role");
//            doRedirect(request, response, role);
        }
        else if (dao.get().isUserExist(login, password)){
            final User.ROLE role = dao.get().getRoleByLoginPassword(login, password);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);

            doRedirect(request, response, role);
        }
        else doRedirect(request, response, UNKNOWN);

    }

    @Override
    public void destroy() {

    }

    private void doRedirect(final HttpServletRequest req, final HttpServletResponse res, final User.ROLE role) throws ServletException, IOException {
        if (role == UNKNOWN) req.getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, res);
        else res.sendRedirect("/home");


    }
}
