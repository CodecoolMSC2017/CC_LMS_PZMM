package com.codecool.web.listener;

import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class WebappContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDaoImpl userService = new UserDaoImpl();
        LoginService loginService = new LoginService(userService);

        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("loginService",loginService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
