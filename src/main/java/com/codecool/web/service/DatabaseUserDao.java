package com.codecool.web.service;

import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        String sql = "SELECT name, email, role FROM users;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }
    }

    @Override
    public User addNewUser(String name, String email, String role, String password) throws InvalidRegistrationException, InvalidEmailAddressException, InvalidPasswordException, EmailAddressAlreadyExistsException, SQLException {
        if (isEmailExists(email)) {
            throw new EmailAddressAlreadyExistsException("Email address is already in use! Try another one!");
        }
        if (!validateEmailAddress(email)) {
            throw new InvalidEmailAddressException("Invalid email address type! Try example@ex.com");
        }
        if (!validatePassword(password)) {
            throw new InvalidPasswordException("Invalid password type! Password must contain uppercase, lowercase and digit characters!");
        }
        if (name.equals("") || email.equals("") || role.equals("") || password.equals("")) {
            throw new InvalidRegistrationException("Please fill all of the fields!");
        }

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (name, email, password, role) " +
                    "VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, role);
            executeUpdate(statement);
            int id = fetchGeneratedId(statement);
            return new User(id, name, email, role, password);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
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

    @Override
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void updateName(int id, String name) throws EmptyFieldException, SQLException {
        if (name == null || name.equals("")) {
            throw new EmptyFieldException("Field name cannot be empty!");
        }

        String sql = "UPDATE users " +
            "SET name = ? " +
            "WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateRole(int id, String role) throws SQLException, EmptyFieldException {
        if (role == null || role.equals("")) {
            throw new EmptyFieldException("Field role cannot be empty!");
        }

        String sql = "UPDATE users " +
            "SET role = ? " +
            "WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateNameAndRole(int id, String name, String role) throws SQLException, EmptyFieldException {
       if (name == null || role == null || name.equals("") || role.equals("")) {
           throw new EmptyFieldException("You have to fill each field!");
       }

        String sql = "UPDATE users " +
            "SET name = ?, role = ? " +
            "WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, role);
            statement.setInt(3, id);
            statement.executeUpdate();
        }
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        return new User(id, name, email, role, password);
    }
}
