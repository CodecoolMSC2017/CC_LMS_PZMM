package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;
import com.codecool.web.service.AssignmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet("/assignmentList")
public class AssignmentServlet  extends HttpServlet{

    private AssignmentDao assignments;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =(User) req.getSession().getAttribute("user");
        assignments = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");


        if (user.getRole().equals("student")) {
            req.setAttribute("assignmentLink","/assignment");
            req.setAttribute("assignments",filterList());
        }
        else {
            req.setAttribute("assignmentLink","/assignmentEdit");
            req.setAttribute("assignments",assignments.getAllAssignments());
        }
        resp.sendRedirect("index.jsp");
    }

    private List<Assignment> filterList() {
        List<Assignment> tempList = new ArrayList<>();
        for (Assignment ass : assignments.getAllAssignments()) {
            if (ass.isPublished()) {
                tempList.add(ass);
            }
        }
        return tempList;
    }

}
