package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.HashMap;
import java.util.List;

public interface AssignmentDao {

    public List<Assignment> getAllAssignments();
    public void addNewAssignment(String question, String title, int maxScore, boolean isPublished) throws EmptyFieldException;
    public void removeAssignment(Assignment assignment);
    public Assignment getAssignmentByTitle(String title);
    public void updateAssignmentTitle(Assignment assignment, String newTitle);
    public void updateMaxScore(Assignment assignment,int score);
    public void setDone(Assignment assignment);
    public void updateIsPublished(Assignment assignment, boolean isPublished);
}
