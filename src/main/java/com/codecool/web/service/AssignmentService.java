package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AssignmentService {

    List<Assignment> getAssignments() throws SQLException;
    HashMap<Assignment, String> getSubmittedAssignmentsByUserId(int userId) throws ServiceException;
    List<Assignment> getUnSubmittedAssignmentsByUserId(int userId) throws SQLException, ServiceException;
    Assignment addAssignment(String title,String question,int maxScore, boolean isPublished) throws SQLException, ServiceException;
    Assignment getAssignment(int id) throws SQLException;
    void updateAssignmentTitle(int id,String newTitle) throws SQLException, ServiceException;
    void updateAssignmentQuestion(int id,String newQuestion) throws SQLException, ServiceException;
    void updateMaxScore(int id,int score) throws SQLException, ServiceException;
    void updateIsPublished(int id, boolean isPublished) throws SQLException;
    Assignment getAssignmentByIdForUser(int userId,int assignmentId) throws ServiceException;
    boolean getIsSubmitted(int userId, int assignmentId) throws ServiceException;
    void addToSubmissions(int assignmentId, int userId, String answer) throws ServiceException;


}
