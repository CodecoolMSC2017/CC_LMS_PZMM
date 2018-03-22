package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurriculumDaoImplTest {

    CurriculumDao cd = new CurriculumDaoImpl();
    List<Curriculum> curriculums = cd.getAllCurriculums();

    @BeforeEach
    void setUp() {
        Assignment assignment = new Assignment(new HashMap<String, List<String>>(), "title", false, new Date());
        Curriculum curriculum = new Curriculum("This is the title.", "This is the content, bla bla bla.", false, assignment);
        curriculums.add(curriculum);
    }

    @Test
    void testGetCurriculumByTitle() {
        //given
        String title = "This is the title.";
        String wrongTitle = "This is the wrong title";
        Curriculum curriculum = null;

        //when
        curriculum = cd.getCurriculumByTitle(title);

        //then
        assertEquals(title, curriculum.getTitle());
        assertNotEquals(wrongTitle, curriculum.getTitle());
    }
}
