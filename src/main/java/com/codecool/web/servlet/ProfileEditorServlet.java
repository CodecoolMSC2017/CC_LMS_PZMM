package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.EmptyFieldException;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/profileeditor")
public class ProfileEditorServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("profileeditor.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userService = (UserDao) req.getServletContext().getAttribute("userService");
        User user = (User) req.getSession().getAttribute("user");

        try {
            userService.updateName(user, req.getParameter("name"));
            req.setAttribute("info", "Modification is successful!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Field name cannot be empty!");
        }
        userService.updateRole(user, req.getParameter("role"));
        doGet(req, resp);
    }
}
