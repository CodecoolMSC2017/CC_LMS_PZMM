package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserDao;
import com.codecool.web.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/protected/userlist")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userService = (UserDao) req.getServletContext().getAttribute("userService");
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);
    }
}
