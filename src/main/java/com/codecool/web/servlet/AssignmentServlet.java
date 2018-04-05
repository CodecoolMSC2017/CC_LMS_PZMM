package com.codecool.web.servlet;

import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/assignment.jsp")
public class AssignmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User)req.getSession().getAttribute("user");
        if(loggedUser == null)
        {
            resp.sendRedirect("index.jsp");
            return;
        }
        if(loggedUser.getRole().equals("mentor")){
            resp.sendRedirect("index.jsp");
        }


    }
}
