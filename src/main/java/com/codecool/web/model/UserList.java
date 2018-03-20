package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    List<User> users;

    private UserList() {
        users = new ArrayList<>();
    }

    private static UserList userList = new UserList();

    public static UserList getUserListInstance() {
        return userList;
    }

    public List<User> getUsers() {
        return users;
    }
}
