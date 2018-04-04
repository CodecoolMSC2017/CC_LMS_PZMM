package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;
import com.codecool.web.service.AssignmentDao;
import com.codecool.web.service.EmptyFieldException;

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
        AssignmentDao assignmentService = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");

        try {
            assignmentService.updateAssignmentTitle(selectedAssignment,req.getParameter("title"));
            assignmentService.updateAssignmentQuestion(selectedAssignment,req.getParameter("question"));
            assignmentService.updateMaxScore(selectedAssignment,Integer.parseInt(req.getParameter("maxScore")));
            req.setAttribute("info", "Modification is successful!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "These fields cannot be empty!");
        }

        assignmentService.updateIsPublished(selectedAssignment, Boolean.parseBoolean(req.getParameter("isPublished")));

        req.setAttribute("selectedAssignment", selectedAssignment);
        req.getRequestDispatcher("protected/assignment.jsp").forward(req, resp);
        resp.sendRedirect("assignment.jsp");
    }
}
