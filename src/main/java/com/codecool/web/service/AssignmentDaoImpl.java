package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

    private final List<Assignment> assignments = new ArrayList<>();

    @Override
    public List<Assignment> getAllAssignments() {
        return assignments;
    }

    @Override
    public void addNewAssignment(String question, String title, int maxScore, boolean isPublished) throws EmptyFieldException {
        if (question.equals("") || title.equals("")) {
            throw new EmptyFieldException();
        }
        Assignment newAssignment = new Assignment(question, title, maxScore, false, isPublished);
        assignments.add(newAssignment);
    }

    @Override
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    @Override
    public Assignment getAssignmentByTitle(String title) {
        for (Assignment assignment:assignments) {
            if (assignment.getTitle().equals(title)) {
                return assignment;
            }
        }
        return null;
    }

    @Override
    public void updateAssignmentTitle(Assignment assignment, String newTitle) throws EmptyFieldException {
        if (newTitle.equals("")) {
            throw new EmptyFieldException();
        }
        assignment.setTitle(newTitle);
    }

    @Override
    public void updateAssignmentQuestion(Assignment assignment, String newQuestion) throws EmptyFieldException {
        if (newQuestion.equals("")) {
            throw new EmptyFieldException();
        }
        assignment.setQuestion(newQuestion);
    }

    @Override
    public void updateMaxScore(Assignment assignment, int newScore) throws EmptyFieldException {
        if (Integer.toString(newScore).equals("")) {
            throw new EmptyFieldException();
        }
        assignment.setMaxScore(newScore);
    }

    @Override
    public void setDone(Assignment assignment) {
        assignment.setDone(true);
    }

    @Override
    public void updateIsPublished(Assignment assignment, boolean isPublished) {
        assignment.setPublished(isPublished);
    }
}
