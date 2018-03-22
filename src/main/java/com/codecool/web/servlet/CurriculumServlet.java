package com.codecool.web.servlet;


import com.codecool.web.model.Curriculum;
import com.codecool.web.model.User;
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

    private CurriculumDao cdi = new CurriculumDaoImpl();
    private List<Curriculum> curriculums = cdi.getAllCurriculums();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =(User) req.getSession().getAttribute("loggedUser");
        curriculums.add(new Curriculum("teszt","12345",true));

        if (user.getRole().equals("student")) {
            req.setAttribute("curriculumLink","/curriculum");
            req.setAttribute("curriculums",filterList());

        }
        else {
            req.setAttribute("curriculumLink","/curriculumEdit");
            req.setAttribute("curriculums",curriculums);

        }

        resp.sendRedirect("index.jsp");
    }

    private List<Curriculum> filterList() {
        List<Curriculum> tempList = new ArrayList<>();
        for (Curriculum curr : curriculums) {
            if (curr.isPublished()) {
                tempList.add(curr);
            }
        }
        return tempList;
    }
}
