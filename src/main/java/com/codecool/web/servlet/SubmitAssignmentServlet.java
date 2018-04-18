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

@WebServlet("/SubmitAssignmentServlet")
public class SubmitAssignmentServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession();
            Assignment selectedAssignment = (Assignment) session.getAttribute("selectedAssignment");
            User loggedInUser = (User) session.getAttribute("user");
            String answer = req.getParameter("answer");
            AssignmentDao assignmentDao = new AssignmentDatabaseDao(connection);
            AssignmentService assignmentService = new SimpleAssignmentService(assignmentDao);
            //Assignment assignment = new Assignment(question, title, maxScore, true, isSubmitted);
            //assignment.setAnswer(answer);
            assignmentService.addToSubmissions(selectedAssignment.getId(),loggedInUser.getId(),answer);
            resp.sendRedirect("protected/index.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
