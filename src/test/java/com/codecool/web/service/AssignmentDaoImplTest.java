package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentDaoImplTest {

    AssignmentDao assignmentService = new AssignmentDaoImpl();

    @BeforeEach
    void setUp() throws EmptyFieldException {
        assignmentService.addNewAssignment("What is testing?", "Test", 10, false);
        assignmentService.addNewAssignment("What is OOP?", "OOP", 10, false);
    }

    @Test
    void testAssignments() throws EmptyFieldException {
        assertNotNull(assignmentService.getAllAssignments());
        assertEquals(2,assignmentService.getAllAssignments().size());
    }

    @Test
    void testGetAssignmentByTitle() {
        assertEquals(assignmentService.getAllAssignments().get(1), assignmentService.getAssignmentByTitle("OOP"));
        assertEquals(assignmentService.getAllAssignments().get(0), assignmentService.getAssignmentByTitle("Test"));
    }

    @Test
    void removeAssignment() {
        assignmentService.removeAssignment(assignmentService.getAssignmentByTitle("OOP"));
        assertEquals(1,assignmentService.getAllAssignments().size());
        assignmentService.removeAssignment(assignmentService.getAssignmentByTitle("Test"));
        assertEquals(0,assignmentService.getAllAssignments().size());
    }

}
