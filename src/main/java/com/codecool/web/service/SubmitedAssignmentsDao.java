package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.List;

public interface SubmitedAssignmentsDao {
    List<Assignment> getAssignmentsByEmail(String email);
   // Assignment getAssignmentForEmailByTitle(String email,String assignmentTitle);
    void addToSubmittedAssignments(String email ,Assignment assignment);
    boolean isSubmitted(String email,String assignmentTitle);
    Assignment getAssigmentForUser(String email, String assignmentTitle);
}
