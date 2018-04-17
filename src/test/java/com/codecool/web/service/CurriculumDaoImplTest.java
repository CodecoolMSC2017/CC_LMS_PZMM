package com.codecool.web.service;

import com.codecool.web.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CurriculumDaoImplTest {
    private CurriculumDao curr = new CurriculumDaoImpl();

    @BeforeEach
    void setUp() throws EmptyFieldException, SQLException {
       curr.addNewCurriculum("Kisbaba","a kisbabak kicsik",true);
       curr.addNewCurriculum("Nagybaba","a nagybabak nagyobbak mint a kicsik",false);
    }
    @Test
    void getAllCurriculums() throws SQLException {
        assertEquals(2,curr.getAllCurriculums().size());
    }

    @Test
    void addNewCurriculum() throws EmptyFieldException, SQLException {
        int sizeBeforeAdd = curr.getAllCurriculums().size();
        curr.addNewCurriculum("Kozepesbaba","a kozepesbabak kicsivel nagyobbak mint a kisbabak",true);
        assertEquals(sizeBeforeAdd+1,curr.getAllCurriculums().size());

    }

    @Test
    void removeCurriculum() throws SQLException {
        int sizeBeforeRemove = curr.getAllCurriculums().size();
        curr.removeCurriculum(curr.getCurriculumByTitle("Nagybaba"));
        assertEquals(sizeBeforeRemove-1,curr.getAllCurriculums().size());
    }
}
