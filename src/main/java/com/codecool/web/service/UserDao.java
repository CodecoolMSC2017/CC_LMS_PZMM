package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public void addNewUser(String name, String email, String role, String password) throws InvalidRegistrationException, InvalidEmailAddressException, InvalidPasswordException, EmailAddressAlreadyExistsException;
    public boolean isEmailExists(String email);
    public boolean validateEmailAddress(String email);
    public boolean validatePassword(String pw);
    public User getUserByEmail(String email);
    public void deleteUser(User user);
    public void updateName(User user, String newName) throws EmptyFieldException;
    public void updateRole(User user, String newRole);
}
