package com.codecool.web.service;

import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> findAllUsers() throws SQLException;
    public User addNewUser(String name, String email, String role, String password) throws InvalidRegistrationException, InvalidEmailAddressException, InvalidPasswordException, EmailAddressAlreadyExistsException, SQLException;
    public boolean isEmailExists(String email) throws SQLException;
    public boolean validateEmailAddress(String email);
    public boolean validatePassword(String pw);
    public User getUserByEmail(String email) throws SQLException;
    public User getUserById(int id) throws SQLException;
    public void updateName(int id, String name) throws EmptyFieldException, SQLException;
    public void updateRole(int id, String name) throws SQLException;
    public void updateNameAndRole(int id, String newName, String role) throws SQLException;
}
