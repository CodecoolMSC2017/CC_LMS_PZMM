package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public void addNewUser(User newUser);
    public boolean isEmailExists(String email);
    public User getUserByEmail(String email);
    public void updateUser(User user, String toUpdate, Object newData);
    public void deleteUser(User user);
}
