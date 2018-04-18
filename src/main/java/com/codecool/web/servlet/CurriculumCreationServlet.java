package com.codecool.web.servlet;

import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.dao.database.CurriculumDatabaseDao;
import com.codecool.web.service.*;
import com.codecool.web.service.exception.EmptyFieldException;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCurriculumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/curriculumcreation")
public class CurriculumCreationServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            CurriculumDao curriculumDao = new CurriculumDatabaseDao(connection);
            CurriculumService curriculumService = new SimpleCurriculumService(curriculumDao);

            String title = req.getParameter("title");
            String content = req.getParameter("content");
            boolean visibility = Boolean.parseBoolean(req.getParameter("ispublished"));
            curriculumService.addNewCurriculum(title,content,visibility);
            req.setAttribute("info", "Curriculum creation is successful!");
        } catch (SQLException | ServiceException e) {
            req.setAttribute("error", e.getMessage());
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Title and content field cannot be empty!");
        }
        req.getRequestDispatcher("newcurriculum.jsp").forward(req, resp);
    }


       /* try {
            curriculumService.addNewCurriculum(title, content, visibility);
            req.setAttribute("info", "Curriculum creation is successful!");
        } catch (EmptyFieldException e) {
            req.setAttribute("error", "Title and content field cannot be empty!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/
}
