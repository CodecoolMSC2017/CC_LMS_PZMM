package com.codecool.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/protected/*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String loginURI = req.getContextPath() + "/login.jsp";

        boolean loggedIn = session != null && session.getAttribute("loggedUser") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(loginURI);
        }
    }
}
