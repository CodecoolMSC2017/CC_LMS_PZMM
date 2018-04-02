package com.codecool.web.servlet;

import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;
import com.codecool.web.service.CurriculumDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/setCurriculum")
public class SetCurriculumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CurriculumDao curriculumDao = (CurriculumDao) req.getServletContext().getAttribute("curriculumService");
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser.getRole().equals("student")) {
            session.setAttribute("selectedCurriculum", curriculumDao.getCurriculumByTitle(req.getParameter("curriculum")));
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
        } else {
            session.setAttribute("selectedCurriculum", curriculumDao.getCurriculumByTitle(req.getParameter("curriculum")));
            req.getRequestDispatcher("protected/curriculumedit.jsp").forward(req, resp);
        }
    }
}
