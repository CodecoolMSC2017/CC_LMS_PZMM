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

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {

    private UserDao udi = new UserDaoImpl();
    private List<User> users = udi.getAllUsers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        req.setAttribute("size", users.size());
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);
    }
}
