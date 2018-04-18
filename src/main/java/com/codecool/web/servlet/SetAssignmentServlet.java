package com.codecool.web.servlet;

import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.dao.database.AssignmentDatabaseDao;
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

@WebServlet("/setAssignment")
public class SetAssignmentServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession();
            //AssignmentDao assignmentDao = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");
            User loggedInUser = (User) session.getAttribute("user");
            AssignmentDao assignmentsDao = new AssignmentDatabaseDao(connection);
            AssignmentService  assignmentService= new SimpleAssignmentService(assignmentsDao);
            if (loggedInUser.getRole().equals("student")) {
                if (assignmentService.getIsSubmitted(loggedInUser.getId(), Integer.parseInt(req.getParameter("assignment")))) {
                    session.setAttribute("selectedAssignment", assignmentService.getAssignmentByIdForUser(loggedInUser.getId(), Integer.parseInt(req.getParameter("assignment"))));
                } else {
                    session.setAttribute("selectedAssignment", assignmentService.getAssignment(Integer.parseInt(req.getParameter("assignment"))));
                }
                // req.getRequestDispatcher("protected/assignment.jsp").forward(req, resp);
                resp.sendRedirect("protected/assignment.jsp");
            } else {
                session.setAttribute("selectedAssignment", assignmentService.getAssignment(Integer.parseInt(req.getParameter("assignment"))));
                //req.getRequestDispatcher("protected/assignmenteditmentor.jsp").forward(req, resp);
                resp.sendRedirect("protected/assignmenteditmentor.jsp");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
