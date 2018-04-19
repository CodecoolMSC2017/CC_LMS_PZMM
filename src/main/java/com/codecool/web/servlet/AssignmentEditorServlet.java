package com.codecool.web.servlet;

import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.dao.database.AssignmentDatabaseDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;
import com.codecool.web.service.*;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleAssignmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/assignmentEditorServlet")
public class AssignmentEditorServlet extends AbstractServlet {
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getServletContext().getAttribute("user");
        if (loggedUser.getRole().equals("student")) {
            resp.sendRedirect("assignment.jsp");
        }
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Assignment selectedAssignment = (Assignment) session.getAttribute("selectedAssignment");


        try (Connection connection = getConnection(req.getServletContext())) {
            AssignmentDao assDao = new AssignmentDatabaseDao(connection);
            AssignmentService assignmentService = new SimpleAssignmentService((AssignmentDatabaseDao) assDao);
            int selectedAssignmentId = selectedAssignment.getId();

            int assignmentId = selectedAssignment.getId();
            String newTitle = req.getParameter("title");
            String newQuestion = req.getParameter("question");
            String newMaxScore = req.getParameter("maxScore");
            String isPublished = req.getParameter("isPublished");
            try {
                assignmentService.updateAssignmentTitle(assignmentId, newTitle);
                assignmentService.updateAssignmentQuestion(assignmentId, newQuestion);
                assignmentService.updateMaxScore(assignmentId, Integer.parseInt(newMaxScore));
                assignmentService.updateIsPublished(assignmentId, Boolean.parseBoolean(isPublished));
                Assignment modifiedAssignment = assignmentService.getAssignment(selectedAssignmentId);
                req.getSession().setAttribute("selectedAssignment", modifiedAssignment);
                req.setAttribute("info", "Modification is done!");
            } catch (ServiceException e) {
                req.setAttribute("error", "Invalid service operation!");
            }
            req.getRequestDispatcher("assignmenteditmentor.jsp").forward(req, resp);
        } catch (SQLException ex) {
            if (SQL_ERROR_CODE_UNIQUE_VIOLATION.equals(ex.getSQLState())) {
                req.setAttribute("error", "Invalid SQL operation!");
            } else {
                throw new ServletException(ex);
            }
        }
    }
}

