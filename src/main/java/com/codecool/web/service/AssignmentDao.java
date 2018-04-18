package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AssignmentDao {

    public List<Assignment> getAllAssignments() throws SQLException;
    public Assignment addNewAssignment(String title, String question, int maxScore, boolean isPublished) throws EmptyFieldException, SQLException;
    public void removeAssignmentById(int id) throws SQLException;
    public Assignment getAssignmentById(int id) throws SQLException;
    public void updateAssignmentTitleById(int id, String newTitle) throws EmptyFieldException, SQLException;
    public void updateAssignmentQuestionById(int id, String newQuestion) throws EmptyFieldException, SQLException;
    public void updateMaxScoreById(int id,int score) throws SQLException;
    public void updateIsDoneById(int id, boolean isDone) throws SQLException;
    public void updateIsPublishedById(int id, boolean isPublished) throws SQLException;
    public void addToSubmittedAssignments(int assignmentId, int userId, String answer) throws SQLException;
    public Assignment fetchAssignment(ResultSet resultSet) throws SQLException;
    public List<Assignment> getSubmittedAssignmentsById(int userId) throws SQLException;
    public List<Assignment> getUnSubmittedAssignmentsById(int userId) throws SQLException;
    public Assignment getAssignmentByUserId(int userId, int assignmentId);
}
