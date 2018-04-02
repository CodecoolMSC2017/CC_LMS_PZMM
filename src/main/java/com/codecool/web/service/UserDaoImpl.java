package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.model.UserList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class UserDaoImpl implements UserDao {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addNewUser(String name, String email, String role, String password) throws InvalidRegistrationException, InvalidEmailAddressException, InvalidPasswordException {
        if (name.equals("") || email.equals("") || role == null || password.equals("")) {
            throw new InvalidRegistrationException();
        } else if (!validateEmailAddress(email)) {
            throw new InvalidEmailAddressException();
        } else if (!validatePassword(email)) {
            throw new InvalidPasswordException();
        }
        User newUser = new User(name,email,role,password);
        users.add(newUser);
    }

    public boolean validatePassword(String pw) {
        char[] pwAsCharArray = pw.toCharArray();
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isDigit = false;
        boolean isLengthProper = false;
        for (char ch :pwAsCharArray) {
            if (Character.isUpperCase(ch)) {
                isUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                isLowerCase = true;
            } else if (Character.isDigit(ch)) {
                isDigit = true;
            } else if (pwAsCharArray.length >= 8) {
                isLengthProper = true;
            }
        }
        return isUpperCase && isLowerCase && isDigit && isLengthProper;
    }

    public boolean validateEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
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
