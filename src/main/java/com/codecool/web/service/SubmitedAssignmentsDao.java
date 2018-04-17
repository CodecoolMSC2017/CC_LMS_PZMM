package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.List;

public interface SubmitedAssignmentsDao {
    List<Assignment> getSubmittedAssignmentsById(int userId);
    Assignment getAssigmentById(int userId, int assignmentId);
    void addToSubmittedAssignments(int userId, int assignmentId, String answer);
}
