package com.codecool.web.servlet;

import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.dao.database.CurriculumDatabaseDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;
import com.codecool.web.service.*;
import com.codecool.web.service.exception.EmptyFieldException;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCurriculumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/curriculumEditorServlet")
public class CurriculumEditorServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession();
            Curriculum selectedCurriculum = (Curriculum) session.getAttribute("selectedCurriculum");
            CurriculumDao curriculumDao = new CurriculumDatabaseDao(connection);
            CurriculumService cs = new SimpleCurriculumService(curriculumDao);
            cs.updateCurriculumTitle(selectedCurriculum, req.getParameter("title"));
            cs.updateContent(selectedCurriculum, req.getParameter("content"));
            Curriculum modifiedCurriculum = cs.getCurriculumById(selectedCurriculum.getId());
            req.getSession().setAttribute("selectedCurriculum", modifiedCurriculum);
            if (req.getParameter("isPublished") != null) {
                cs.updateIsPublished(selectedCurriculum, true);
            } else {
                cs.updateIsPublished(selectedCurriculum, false);
            }

            req.setAttribute("info", "Modification is done!");
        } catch (SQLException | ServiceException e) {
            req.setAttribute("error", e.getMessage());
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Fill the title and content!");
        }
        req.getRequestDispatcher("curriculumedit.jsp").forward(req, resp);

    }
      /*  try {
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
        }*/

}
