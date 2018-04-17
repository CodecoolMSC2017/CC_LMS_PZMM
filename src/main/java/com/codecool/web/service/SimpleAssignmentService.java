package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.SQLException;
import java.util.List;

public class SimpleAssignmentService implements AssignmentService{

    private final AssignmentDatabaseDao assDao;

    public SimpleAssignmentService(AssignmentDatabaseDao assDao) {
        this.assDao = assDao;
    }

    @Override
    public List<Assignment> getAssignments() throws SQLException {
        return assDao.getAllAssignments();
    }

    @Override
    public Assignment addAssignment(String title, String question, int maxScore, boolean isDone, boolean isPublished) throws SQLException, ServiceException {
        try {
            return assDao.addNewAssignment(title, question, maxScore, isDone, isPublished);
        } catch (EmptyFieldException ex) {
            throw new ServiceException("Title and question cannot be null!");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }


    @Override
    public Assignment getAssignment(int id) throws SQLException {
        return assDao.getAssignmentById(id);
    }

    @Override
    public void updateAssignmentTitle(int id, String newTitle) throws SQLException, ServiceException {
        try {
            assDao.updateAssignmentTitleById(id,newTitle);
        } catch (EmptyFieldException ex) {
            throw new ServiceException("Title cannot be null");
        }
    }

    @Override
    public void updateAssignmentQuestion(int id, String newQuestion) throws SQLException, ServiceException {
        try {
            assDao.updateAssignmentQuestionById(id,newQuestion);
        } catch (EmptyFieldException ex) {
            throw new ServiceException("Question cannot be null");
        }
    }

    @Override
    public void updateMaxScore(int id, int score) throws SQLException, ServiceException {
        try {
            assDao.updateMaxScoreById(id,score);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void updateIsDone(int id, boolean isDone) throws SQLException {
        assDao.updateIsDoneById(id, isDone);
    }

    @Override
    public void updateIsPublished(int id, boolean isPublished) throws SQLException {
        assDao.updateIsPublishedById(id,isPublished);
    }
}