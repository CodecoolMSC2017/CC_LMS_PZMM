package com.codecool.web.service;

import com.codecool.web.model.User;

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
    public void addNewUser(String name, String email, String role, String password) throws InvalidRegistrationException, InvalidEmailAddressException, InvalidPasswordException, EmailAddressAlreadyExistsException {
        if (name.equals("") || email.equals("") || role == null || password.equals("")) {
            throw new InvalidRegistrationException();
        } else if (!validateEmailAddress(email)) {
            throw new InvalidEmailAddressException();
        } else if (!validatePassword(password)) {
            throw new InvalidPasswordException();
        } else if (isEmailExists(email)) {
            throw new EmailAddressAlreadyExistsException();
        }
        User newUser = new User(name,email,role,password);
        users.add(newUser);
    }

    public boolean validatePassword(String pw) {
        //char[] pwAsCharArray = pw.toCharArray();
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isDigit = false;
        boolean isLengthProper = false;
        for (int i = 0; i < pw.length(); i++) {
            char ch = pw.charAt(i);
            if (pw.length() >= 8) {
                isLengthProper = true;
            }
            if (Character.isUpperCase(ch)) {
                isUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                isLowerCase = true;
            } else if (Character.isDigit(ch)) {
                isDigit = true;
            }
        }
        if (isUpperCase && isLowerCase && isDigit && isLengthProper) {
            return true;
        } else {
            return false;
        }
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



    public void updateName(User user, String newName) throws EmptyFieldException {
        if (newName == null || newName.equals("")) {
            throw new EmptyFieldException();
        }
        user.setName(newName);
    }

    @Override
    public void updateRole(User user, String newRole) {
        user.setRole(newRole);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
