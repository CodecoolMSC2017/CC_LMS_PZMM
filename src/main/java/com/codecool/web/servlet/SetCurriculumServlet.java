package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.dao.database.CurriculumDatabaseDao;
import com.codecool.web.service.CurriculumService;
import com.codecool.web.service.simple.SimpleCurriculumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/setCurriculum")
public class SetCurriculumServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession();
            CurriculumDao curriculumDao = new CurriculumDatabaseDao(connection);
            CurriculumService curriculumService = new SimpleCurriculumService(curriculumDao);
            User loggedInUser = (User) session.getAttribute("user");
            if (loggedInUser.getRole().equals("student")) {
                session.setAttribute("selectedCurriculum", curriculumService.getCurriculumByTitle(req.getParameter("curriculum")));
                resp.sendRedirect("protected/curriculum.jsp");
            } else {
                session.setAttribute("selectedCurriculum", curriculumService.getCurriculumByTitle(req.getParameter("curriculum")));
                resp.sendRedirect("protected/curriculumedit.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
