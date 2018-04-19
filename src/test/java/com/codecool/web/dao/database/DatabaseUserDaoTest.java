package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.service.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseUserDaoTest {

    private String user=System.getProperty("user");
    private String password = System.getProperty("password");
    private String database = System.getProperty("database");
    private String url = "jdbc:postgresql://localhost/" + database + "?user=" + user + "&password=" + password;
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    UserDao userDao = new DatabaseUserDao(connection);

    @BeforeEach
    void setUp() throws SQLException, EmailAddressAlreadyExistsException, InvalidPasswordException, InvalidEmailAddressException, InvalidRegistrationException {
        String sql = "DROP TABLE IF EXISTS users_assignments;\n" +
                    "DROP TABLE IF EXISTS users;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        String sqlinit = "CREATE TABLE public.users (\n" +
                        "id serial PRIMARY KEY,\n" +
                        "name text NOT NULL,\n" +
                        "email text NOT NULL,\n" +
                        "password text NOT NULL,\n" +
                        "role text NOT NULL\n" +
                        ");";
        statement.executeUpdate(sqlinit);
        userDao.addNewUser("Galyas Attila","galyas@attila.hu","mentor","Chickenbody123");
        userDao.addNewUser("Chicken Body","chicken@body.hu","student","Bömellény123");

    }

    @Test
    void findAllUsers() throws SQLException {
        assertEquals(2,userDao.findAllUsers().size());
    }

    @Test
    void addNewUser() throws SQLException, EmailAddressAlreadyExistsException, InvalidPasswordException, InvalidEmailAddressException, InvalidRegistrationException {
        assertEquals(2,userDao.findAllUsers().size());
        userDao.addNewUser("Doktor Albán","its@mylife.hu","student","Password123");
        assertEquals(3,userDao.findAllUsers().size());

    }

    @Test
    void isEmailExists() throws SQLException {
        assertEquals(true,userDao.isEmailExists("chicken@body.hu"));
    }

    @Test
    void validateEmailAddress() {
        assertEquals(true,userDao.validateEmailAddress("test@email.com"));

        assertEquals(false,userDao.validateEmailAddress("wrongemail.hu"));

        assertEquals(false,userDao.validateEmailAddress("wrongemail@asdasd"));

        assertEquals(false,userDao.validateEmailAddress("@wrongemail.hu"));

    }

    @Test
    void validatePassword() {
        assertEquals(true,userDao.validatePassword("Correctpassword123"));

        assertEquals(false,userDao.validatePassword("incorrectpassword123"));

        assertEquals(false,userDao.validatePassword("Incorrectpassword"));

        assertEquals(false,userDao.validatePassword("12345678"));

        assertEquals(false,userDao.validatePassword("1234"));

        assertEquals(false,userDao.validatePassword("asd"));

        assertEquals(false,userDao.validatePassword("ASD"));

        assertEquals(false,userDao.validatePassword("ASSDAG"));

        assertEquals(false,userDao.validatePassword("asdFGSDGdfaf"));

        assertEquals(true,userDao.validatePassword("AkAk6K3M424ca"));
    }

    @Test
    void getUserByEmail() throws SQLException {
       assertEquals("Galyas Attila",userDao.getUserByEmail("galyas@attila.hu").getName());
    }

    @Test
    void getUserById() throws SQLException {
        assertEquals("Galyas Attila",userDao.getUserById(1).getName());
        assertEquals("Chicken Body",userDao.getUserById(2).getName());

    }

    @Test
    void updateName() throws EmptyFieldException, SQLException {
        assertEquals("Galyas Attila",userDao.getUserById(1).getName());
        userDao.updateName(1,"Lezli");
        assertEquals("Lezli",userDao.getUserById(1).getName());
        assertEquals(false,userDao.getUserById(1).getName().equals("Galyas Attila"));
    }

    @Test
    void updateRole() throws SQLException, EmptyFieldException {
        assertEquals("mentor",userDao.getUserById(1).getRole());
        userDao.updateRole(1,"student");
        assertEquals("student",userDao.getUserById(1).getRole());

    }

    @Test
    void updateNameAndRole() throws SQLException, EmptyFieldException {
        assertEquals("mentor",userDao.getUserById(1).getRole());
        assertEquals("Galyas Attila",userDao.getUserById(1).getName());

        userDao.updateNameAndRole(1,"Lezli","student");

        assertEquals("student",userDao.getUserById(1).getRole());
        assertEquals("Lezli",userDao.getUserById(1).getName());


        assertEquals("Chicken Body",userDao.getUserById(2).getName());
        assertEquals("student",userDao.getUserById(2).getRole());

        userDao.updateNameAndRole(2,"Body Chicken","mentor");


        assertEquals("Body Chicken",userDao.getUserById(2).getName());
        assertEquals("mentor",userDao.getUserById(2).getRole());




    }
}
