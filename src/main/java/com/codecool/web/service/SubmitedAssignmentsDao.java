package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.List;

public interface SubmitedAssignmentsDao {
    List<Assignment> getAssignmentsById(int userId);
    void addToSubmittedAssignments(int userId, int assignmentId, String answer);
    Assignment getAssigmentById(int userId, int assignmentId);
    String getAnswerByUserIdAndAssignmentId(int userId, int assignmentId);
}
