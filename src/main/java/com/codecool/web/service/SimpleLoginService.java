package com.codecool.web.service;

import com.codecool.web.model.User;

import java.sql.SQLException;

public class SimpleLoginService implements LoginService {

    private final UserDao userDao;

    public SimpleLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String email, String password) throws SQLException, ServiceException {
        User user = userDao.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new ServiceException("Incorrect email and password combination!");
        }
        return user;
    }
}
