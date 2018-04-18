package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/SubmitAssignmentServlet")
public class SubmitAssignmentServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Assignment selectedAssignment = (Assignment) session.getAttribute("selectedAssignment");
        User loggedInUser = (User) session.getAttribute("user");
        String title = selectedAssignment.getTitle();
        String question = selectedAssignment.getQuestion();
        int maxScore = selectedAssignment.getMaxScore();
        String answer = req.getParameter("answer");
        boolean isSubmitted = selectedAssignment.isPublished();
        SubmittedAssignmentsDao submittedAssignmentsDao = (SubmittedAssignmentsDao) req.getServletContext().getAttribute("submittedAssignmentsService");
        Assignment assignment = new Assignment(question,title,maxScore,true,isSubmitted);
        assignment.setAnswer(answer);
        submittedAssignmentsDao.addToSubmittedAssignments(loggedInUser.getEmail(),assignment);
        resp.sendRedirect("protected/index.jsp");

    }
}
