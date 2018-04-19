package com.codecool.web.servlet;

import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.dao.database.AssignmentDatabaseDao;
import com.codecool.web.service.*;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleAssignmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/newassignment")
public class NewAssignmentServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("newassignment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {

            AssignmentDao assignmentDataBaseDao = new AssignmentDatabaseDao(connection);
            AssignmentService assignmentService = new SimpleAssignmentService(assignmentDataBaseDao);

            String title = req.getParameter("title");
            String question = req.getParameter("question");
            String maxScore = req.getParameter("maxscore");
            boolean isPublished = Boolean.parseBoolean(req.getParameter("visibility"));

            assignmentService.addAssignment(title, question, Integer.parseInt(maxScore), isPublished);
            req.setAttribute("info", "New assignment is added!");
        } catch (SQLException e) {
            throw new ServletException();
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
        }
        req.getRequestDispatcher("newassignment.jsp").forward(req, resp);
    }
}
