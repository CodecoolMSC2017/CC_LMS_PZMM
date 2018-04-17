package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.SQLException;
import java.util.List;

public interface AssignmentService {

    List<Assignment> getAssignments() throws SQLException;
    Assignment addAssignment(String title,String question,int maxScore,boolean isDone,boolean isPublished) throws SQLException, ServiceException;
    Assignment getAssignment(int id) throws SQLException;
    void updateAssignmentTitle(int id,String newTitle) throws SQLException, ServiceException;
    void updateAssignmentQuestion(int id,String newQuestion) throws EmptyFieldException, SQLException, ServiceException;
    void updateMaxScore(int id,int score) throws SQLException, ServiceException;
    void updateIsDone(int id, boolean isDone) throws SQLException;
    void updateIsPublished(int id, boolean isPublished) throws SQLException;


}