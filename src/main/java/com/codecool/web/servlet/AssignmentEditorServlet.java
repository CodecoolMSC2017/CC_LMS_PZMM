package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/assignmentEditorServlet")
public class AssignmentEditorServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Assignment selectedAssignment = (Assignment) session.getAttribute("selectedAssignment");

        selectedAssignment.setTitle(req.getParameter("title"));
        selectedAssignment.setQuestion(req.getParameter("question"));
        selectedAssignment.setMaxScore(Integer.parseInt(req.getParameter("maxScore")));
        selectedAssignment.setPublished(Boolean.parseBoolean(req.getParameter("isPublished")));

        req.setAttribute("selectedAssignment", selectedAssignment);
        req.getRequestDispatcher("protected/assignment.jsp").forward(req, resp);
        resp.sendRedirect("assignment.jsp");
    }
}
