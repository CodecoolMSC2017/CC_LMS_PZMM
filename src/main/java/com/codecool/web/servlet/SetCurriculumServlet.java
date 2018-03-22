package com.codecool.web.servlet;

import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;

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
        User loggedInUser = (User) session.getAttribute("loggedUser");
        if (loggedInUser.getRole().equals("student")) {
            Curriculum curriculum = (Curriculum) req.getAttribute("selectedCurriculum");
            req.setAttribute("curriculum", curriculum);
            req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
        } else {
            Curriculum curriculum = (Curriculum) req.getAttribute("selectedCurriculum");
            req.setAttribute("curriculum", curriculum);
            req.getRequestDispatcher("curriculumedit.jsp").forward(req, resp);
        }
    }
}
