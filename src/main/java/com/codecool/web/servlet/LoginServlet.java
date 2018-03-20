package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email.equals("")|| password.equals("")){
            return;
            //WrongEmail or Password EXCEPTION
        }
        // Check for existence of email
        UserDaoImpl userDao = new UserDaoImpl();
        if(!userDao.isEmailExists(email)){
            return;
            //WrongEmail Exception
        }
        User user = new User(email,password);
        user.setLogedIn(true);

        resp.sendRedirect("index.jsp");
    }
}
