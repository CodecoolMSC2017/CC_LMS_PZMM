package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userService = (UserDao) req.getServletContext().getAttribute("userService");
        LoginService loginService = (LoginService) req.getServletContext().getAttribute("loginService");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (loginService.login(email, password)) {
            User user = userService.getUserByEmail(email);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("protected/index.jsp");
        } else {
            req.setAttribute("error", "No such user in database!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
