package com.codecool.web.servlet;


import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;
import com.codecool.web.service.AssignmentDao;
import com.codecool.web.service.CurriculumDao;
import com.codecool.web.service.CurriculumDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/curriculumList")
public class CurriculumServlet extends HttpServlet{

    private CurriculumDao curriculums;
    private AssignmentDao assignments;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =(User) req.getSession().getAttribute("user");
        curriculums = (CurriculumDao)req.getServletContext().getAttribute("curriculumService");
        assignments = (AssignmentDao) req.getServletContext().getAttribute("assignmentService");

        if (user.getRole().equals("student")) {
            req.setAttribute("curriculumLink","/curriculum");
            req.setAttribute("curriculums",filterList());
            req.setAttribute("assignmentLink","/assignment");
            req.setAttribute("assignments",filterAssignmentList());

        }
        else {
            req.setAttribute("curriculumLink","/curriculumEdit");
            req.setAttribute("curriculums",curriculums.getAllCurriculums());
            req.setAttribute("assignmentLink","/assignmenteditmentor");
            req.setAttribute("assignments",assignments.getAllAssignments());

        }

        resp.sendRedirect("index.jsp");
    }

    private List<Curriculum> filterList() {
        List<Curriculum> tempList = new ArrayList<>();
        for (Curriculum curr : curriculums.getAllCurriculums()) {
            if (curr.isPublished()) {
                tempList.add(curr);
            }
        }
        return tempList;
    }

    private List<Assignment> filterAssignmentList() {
        List<Assignment> tempList = new ArrayList<>();
        for (Assignment ass : assignments.getAllAssignments()) {
            if (ass.isPublished()) {
                tempList.add(ass);
            }
        }
        return tempList;
    }
}
