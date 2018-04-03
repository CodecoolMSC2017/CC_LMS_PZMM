package com.codecool.web.servlet;

import com.codecool.web.service.AssignmentDao;
import com.codecool.web.service.EmptyFieldException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("protected/newassignment")
public class NewAssignmentServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("newassignment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AssignmentDao assignmentDao = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");

        String title = req.getParameter("title");
        String question = req.getParameter("question");
        String maxScore = req.getParameter("maxscore");
        String visibility = req.getParameter("visibility");

        try {
            boolean isPublished = false;
            if (visibility.equals("published")) {
                isPublished = true;
            } else if (visibility.equals("unpublished")) {
                isPublished = false;
            }
            assignmentDao.addNewAssignment(question, title, Integer.parseInt(maxScore), isPublished);
            req.setAttribute("info", "New assignment is added!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Fill title and question please!");
        }

    }
}
