package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public boolean isEmailExists(String email);
    public User getUserByEmail(String email);
    public void updateUser(User user);
    public void deleteUser(User user);
}
