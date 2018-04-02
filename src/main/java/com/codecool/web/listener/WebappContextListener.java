package com.codecool.web.listener;

import com.codecool.web.model.User;
import com.codecool.web.service.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao userService = new UserDaoImpl();
        LoginService loginService = new LoginService(userService);
        User adminMentor = new User("MentorAdmin", "a@a.hu","mentor","a");
        User adminStudent = new User("StudentAdmin", "b@b.hu","student","a");
        userService.getAllUsers().add(adminMentor);
        userService.getAllUsers().add(adminStudent);

        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("loginService",loginService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
