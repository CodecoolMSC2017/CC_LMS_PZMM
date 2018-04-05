package com.codecool.web.servlet;

import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;
import com.codecool.web.service.CurriculumDao;
import com.codecool.web.service.EmptyFieldException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/protected/curriculumEditorServlet")
public class CurriculumEditorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Curriculum selectedCurriculum = (Curriculum) session.getAttribute("selectedCurriculum");
        CurriculumDao curriculumDao = (CurriculumDao) req.getServletContext().getAttribute("curriculumService");

        try {
            curriculumDao.updateCurriculumTitle(selectedCurriculum, req.getParameter("title"));
            curriculumDao.updateContent(selectedCurriculum, req.getParameter("content"));
            req.setAttribute("info", "Modification is done!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Fill the title and content!");
        }
        if(req.getParameter("isPublished") != null){
            curriculumDao.updateIsPublished(selectedCurriculum, true);
        }
        else{
            curriculumDao.updateIsPublished(selectedCurriculum, false);

        }
        req.getRequestDispatcher("curriculumedit.jsp").forward(req, resp);
    }
}
