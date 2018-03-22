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

@WebServlet("/curriculumEditorServlet")
public class CurriculumEditorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Curriculum selectedCurriculum = (Curriculum) session.getAttribute("selectedCurriculum");

        selectedCurriculum.setTitle(req.getParameter("title"));
        selectedCurriculum.setContent(req.getParameter("content"));
        selectedCurriculum.setPublished(Boolean.parseBoolean(req.getParameter("isPublished")));

        req.setAttribute("selectedCurriculum", selectedCurriculum);
        req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
        resp.sendRedirect("curriculum.jsp");
    }
}
