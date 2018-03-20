package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");
        if (name.equals("") || email.equals("") || role.equals("") || password.equals("")) {
            return;
        }

        User user = new User(name,email,role,password);

        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.isEmailExists(email)){
            return;
        }
        userDao.addNewUser(user);
        resp.sendRedirect("register.jsp");

    }
}
