package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {

    UserList userList = UserList.getUserListInstance();
    List<User> users = userList.getUsers();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addNewUser(User newUser) {
        users.add(newUser);
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

    public void updateBrith(User user, Object newData) {
        user.setBirth((Date) newData);
    }

    public void updateGender(User user, Object newData) {
        user.setGender((String) newData);
    }

    public void updatePassword(User user, Object newData) {
        user.setPassword((String) newData);
    }

    public void updateEmail(User user, Object newData) {
        user.setEmail((String) newData);
    }

    public void updateName(User user, Object newName) {
        user.setName((String) newName);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
