package com.codecool.web.servlet;


import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.dao.database.AssignmentDatabaseDao;
import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.dao.database.CurriculumDatabaseDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;
import com.codecool.web.service.*;
import com.codecool.web.service.simple.SimpleAssignmentService;
import com.codecool.web.service.simple.SimpleCurriculumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/curriculumList")
public class CurriculumServlet extends AbstractServlet {
    private CurriculumService curriculumService;
    private AssignmentService assignmentService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            User user = (User) req.getSession().getAttribute("user");
            CurriculumDao curriculums = new CurriculumDatabaseDao(connection);
            AssignmentDao assignments = new AssignmentDatabaseDao(connection);
            curriculumService = new SimpleCurriculumService(curriculums);
            assignmentService = new SimpleAssignmentService(assignments);


            if (user.getRole().equals("student")) {
                req.setAttribute("curriculumLink", "/curriculum");
                req.setAttribute("curriculums", filterList());
                req.setAttribute("assignmentLink", "/assignment");
                req.setAttribute("assignments", filterAssignmentList());

            } else {
                req.setAttribute("curriculumLink", "/curriculumEdit");
                req.setAttribute("curriculums", curriculumService.getAllCurriculums());
                req.setAttribute("assignmentLink", "/assignmenteditmentor");
                req.setAttribute("assignments", assignmentService.getAssignments());

            }

            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Curriculum> filterList() throws SQLException {
        List<Curriculum> tempList = new ArrayList<>();
        for (Curriculum curr : curriculumService.getAllCurriculums()) {
            if (curr.isPublished()) {
                tempList.add(curr);
            }
        }
        return tempList;
    }

    private List<Assignment> filterAssignmentList() throws SQLException {
        List<Assignment> tempList = new ArrayList<>();
        for (Assignment ass : assignmentService.getAssignments()) {
            if (ass.isPublished()) {
                tempList.add(ass);
            }
        }
        return tempList;
    }
}
