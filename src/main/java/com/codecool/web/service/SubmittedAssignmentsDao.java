package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.SQLException;
import java.util.List;

public interface SubmittedAssignmentsDao {
    List<Assignment> getSubmittedAssignmentsById(int userId) throws SQLException;
    List<Assignment> getUnSubmittedAssignmentsById(int userId) throws SQLException;
    Assignment getAssignmentByUserId(int userId, int assignmentId);
    void addToSubmittedAssignments(int userId, int assignmentId, String answer);
}
