package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.List;

public interface UserDao {

    UserList userList = UserList.getInstance();
    List<User> users = userList.getUsers;

    public List<User> getAllUsers();
    public User getUserById(int id);
    public void updateUser(User user);
    public void deleteUser(User user);
}
