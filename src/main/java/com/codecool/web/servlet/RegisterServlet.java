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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userService = (UserDao) req.getServletContext().getAttribute("userService");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        try {
            userService.addNewUser(name, email,role,password);
            req.setAttribute("info", "Registration is successful!");
        } catch (InvalidRegistrationException e) {
            req.setAttribute("error", "Please fill all of the fields!");
        } catch (InvalidEmailAddressException e) {
            req.setAttribute("error", "Invalid email address type! Try example@ex.com");
        } catch (InvalidPasswordException e) {
            req.setAttribute("error", "Invalid password type! Password must contain uppercase, lowercase and digit characters!");
        } catch (EmailAddressAlreadyExistsException e) {
            req.setAttribute("error", "Email address is already in use! Try another one!");
        }
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
