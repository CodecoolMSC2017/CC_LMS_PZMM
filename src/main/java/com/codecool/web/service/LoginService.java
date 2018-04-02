package com.codecool.web.service;

import com.codecool.web.model.User;

public final class LoginService {

    private final UserDao userService;

    public LoginService(UserDao userService) {
        this.userService = userService;
    }

    public boolean login(String email, String password) {
        for (User user : userService.getAllUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
