package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.List;

public class UserDaoImpl implements UserDao {

    UserList userList = UserList.getUserListInstance();
    List<User> users = userList.getUsers();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean isEmailExists(String email) {
        for (User user :users) {
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
