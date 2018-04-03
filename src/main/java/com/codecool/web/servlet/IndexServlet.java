package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.AssignmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        AssignmentDao assignmentDao = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");

        req.setAttribute("user", user);
        req.setAttribute("assignments", assignmentDao.getAllAssignments());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
