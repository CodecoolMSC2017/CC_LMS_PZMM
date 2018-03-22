package com.codecool.web.service;

import com.codecool.web.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDao udi = new UserDaoImpl();
    List<User> users = udi.getAllUsers();

    @BeforeEach
    void setUp() {
        users.add(new User("Zoli", "zolivok@gmail.com", "student", "kisbaba"));
        users.add(new User("Peti", "petivok@gmail.com", "student", "kisbaba"));
        users.add(new User("Martin", "Martinvok@gmail.com", "student", "kisbaba"));
    }

    @Test
    void testIsEmailExists() {
        //given
        String email1 = "Martinvok@gmail.com";
        String email2 = "asdasd@gmail.com";

        //when
        boolean isEmailExists1 = udi.isEmailExists(email1);
        boolean isEmailExists2 = udi.isEmailExists(email2);

        //then
        assertTrue(isEmailExists1);
        assertFalse(isEmailExists2);
    }

    @Test
    void testGetUserByEmail() {
        //given
        String email = "petivok@gmail.com";
        String wrongEmail = "asdasd@gmail.com";
        User peti;
        User zoli;

        //when
        peti = udi.getUserByEmail(email);
        zoli = udi.getUserByEmail(wrongEmail);

        //then
        assertEquals("Peti", peti.getName());
        assertEquals("petivok@gmail.com", peti.getEmail());
        assertEquals("student", peti.getRole());
        assertEquals("kisbaba", peti.getPassword());
        assertNull(zoli);
    }
}
