package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;
import com.codecool.web.service.AssignmentDao;
import com.codecool.web.service.SubmitedAssignmentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/setAssignment")
public class SetAssignmentServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AssignmentDao assignmentDao = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");
        User loggedInUser = (User) session.getAttribute("user");
        SubmitedAssignmentsDao submittedAssignmentsDao = (SubmitedAssignmentsDao) req.getServletContext().getAttribute("submittedAssignmentsService");
        if (loggedInUser.getRole().equals("student")) {
            if(submittedAssignmentsDao.isSubmitted(loggedInUser.getEmail(),req.getParameter("assignment"))){
                session.setAttribute("selectedAssignment", submittedAssignmentsDao.getAssigmentForUser(loggedInUser.getEmail(),req.getParameter("assignment")));
            }
            else {
                session.setAttribute("selectedAssignment", assignmentDao.getAssignmentByTitle(req.getParameter("assignment")));
            }
           // req.getRequestDispatcher("protected/assignment.jsp").forward(req, resp);
            resp.sendRedirect("protected/assignment.jsp");
        } else {
            session.setAttribute("selectedAssignment", assignmentDao.getAssignmentByTitle(req.getParameter("assignment")));
            //req.getRequestDispatcher("protected/assignmenteditmentor.jsp").forward(req, resp);
            resp.sendRedirect("protected/assignmenteditmentor.jsp");

        }
    }
}
