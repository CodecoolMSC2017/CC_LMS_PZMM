package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubmittedAssignmentsDaoImplTest {
    SubmittedAssignmentsDao assDao = new SubmittedAssignmentsDaoImpl();
    @BeforeEach
    void setUp() {

        assDao.addToSubmittedAssignments("email@a.com",new Assignment("Kisbaba?","Kisbaba",50,true,true));
        assDao.addToSubmittedAssignments("email@a.com",new Assignment("Nagybaba?","Nagybaba",100,true,true));
        assDao.addToSubmittedAssignments("email2@a.com",new Assignment("Nagybaba?","Nagybaba",100,true,true));

    }

    @Test
    void getAssignmentsByEmail() {
        assertEquals(2,assDao.getAssignmentsByEmail("email@a.com").size());
        assertEquals(1,assDao.getAssignmentsByEmail("email2@a.com").size());
    }

    @Test
    void addToSubmittedAssignments() {
        int submittedAssigments = assDao.getAssignmentsByEmail("email@a.com").size();
        assDao.addToSubmittedAssignments("email@a.com",new Assignment("Kozepesbaba?","Kozepesbaba",50,true,false));
        assertEquals(submittedAssigments+1,assDao.getAssignmentsByEmail("email@a.com").size());
    }

    @Test
    void getAssigmentForUser() {
        assertEquals("Kisbaba",assDao.getAssigmentForUser("email@a.com","Kisbaba").getTitle());
    }
}
