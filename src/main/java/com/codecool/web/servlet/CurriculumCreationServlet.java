package com.codecool.web.servlet;

import com.codecool.web.service.CurriculumDao;
import com.codecool.web.service.EmptyFieldException;
import com.codecool.web.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/curriculumcreation")
public class CurriculumCreationServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurriculumDao curriculumService = (CurriculumDao) req.getServletContext().getAttribute("curriculumService");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        boolean visibility = Boolean.parseBoolean(req.getParameter("ispublished"));

        try {
            curriculumService.addNewCurriculum(title, content, visibility);
            req.setAttribute("info", "Curriculum creation is successful!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Title and content field cannot be empty!");
        }
        req.getRequestDispatcher("newcurriculum.jsp").forward(req, resp);
    }
}
