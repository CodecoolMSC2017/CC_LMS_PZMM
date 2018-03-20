package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public void addNewUser(User newUser);
    public boolean isEmailExists(String email);
    public User getUserByEmail(String email);
    public void deleteUser(User user);
    public void updateBrith(User user, Object newData);
    public void updateGender(User user, Object newData);
    public void updatePassword(User user, Object newData);
    public void updateEmail(User user, Object newData);
    public void updateName(User user, Object newName);
}
