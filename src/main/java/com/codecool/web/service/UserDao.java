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
    public void updateBirth(User user, Object newData);
    public void updateGender(User user, Object newData);
    public void updatePassword(User user, Object newData);
    public void updateEmail(User user, Object newData);
    public void updateName(User user, Object newName);
}
