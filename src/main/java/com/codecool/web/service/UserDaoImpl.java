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

    @Override
    public void updateUser(User user, String toUpdate, Object newData) {
        if (toUpdate.equals("name")) {
            updateName(user, newData);
        } else if (toUpdate.equals("email")) {
            updateEmail(user, newData);
        } else if (toUpdate.equals("password")) {
            updatePassword(user, newData);
        } else if (toUpdate.equals("gender")) {
            updateGender(user, newData);
        } else if (toUpdate.equals("birth")) {
            updateBrith(user, newData);
        }
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
