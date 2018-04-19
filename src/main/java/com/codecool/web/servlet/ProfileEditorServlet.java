package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.service.exception.EmptyFieldException;
import com.codecool.web.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/profileeditor")
public class ProfileEditorServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            User user = userDao.getUserByEmail(sessionUser.getEmail());
            req.getSession().setAttribute("user", user);
            req.setAttribute("user", user);
        } catch (SQLException e) {
            throw new ServletException();
        }
        req.getRequestDispatcher("profileeditor.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);

            int id = user.getId();
            String name = req.getParameter("name");
            String role = req.getParameter("role");

            userDao.updateNameAndRole(id, name, role);
            req.setAttribute("info", "Modification is successful!");
        } catch (SQLException e) {
            throw new ServletException();
        } catch (EmptyFieldException e) {
            req.setAttribute("error", e.getMessage());
        }

        doGet(req, resp);
    }
}
