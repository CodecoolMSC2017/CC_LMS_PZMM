package com.codecool.web.servlet;

import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.AssignmentDatabaseDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;
import com.codecool.web.service.AssignmentService;
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

@WebServlet("/submissionServlet")
public class SubmissionServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Assignment selectedAssignment = (Assignment) session.getAttribute("selectedAssignment");
        User sessionUser = (User) req.getSession().getAttribute("user");


        try (Connection connection = getConnection(req.getServletContext())) {
            AssignmentDao assDao = new AssignmentDatabaseDao(connection);
            AssignmentService assService = new SimpleAssignmentService(assDao);
            boolean isDone = assService.getIsSubmitted(sessionUser.getId(), selectedAssignment.getId());
            req.setAttribute("done", isDone);
            req.getRequestDispatcher("assignment.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
