package com.codecool.web.servlet;

import com.codecool.web.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/userlist")
public class UserListServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userService = (UserDao) req.getServletContext().getAttribute("userService");
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);
    }
}
