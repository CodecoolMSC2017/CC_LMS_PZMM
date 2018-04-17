package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String role = req.getParameter("role");
            String password = req.getParameter("password");

            User user = userDao.addNewUser(name, email, role, password);
            req.setAttribute("info", "Registration is successful!");
        } catch (SQLException e) {
            throw new ServletException();
        } catch (InvalidPasswordException e) {
            req.setAttribute("error", e.getMessage());
        } catch (InvalidRegistrationException e) {
            req.setAttribute("error", e.getMessage());
        } catch (EmailAddressAlreadyExistsException e) {
            req.setAttribute("error", e.getMessage());
        } catch (InvalidEmailAddressException e) {
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
