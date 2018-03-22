package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email.equals("")|| password.equals("")){
            resp.sendRedirect("login.jsp");
            //alert
            return;
        }
        UserDaoImpl userDao = new UserDaoImpl();
        if(!userDao.isEmailExists(email)){
            resp.sendRedirect("index.jsp");
            //alert
            return;
        }
        User user = userDao.getUserByEmail(email);
        user.setLogedIn(true);
        HttpSession session = req.getSession();
        session.setAttribute("isLoggedIn",true);
        session.setAttribute("loggedUser",user);
        resp.sendRedirect("index.jsp");

    }
}
