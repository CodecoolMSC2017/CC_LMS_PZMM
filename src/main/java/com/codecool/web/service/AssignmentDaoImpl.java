package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

    private final List<Assignment> assignments = new ArrayList<>();

    @Override
    public List<Assignment> getAllAssignments() {
        return assignments;
    }

    @Override
    public void addNewAssignment(Assignment newAssignment) {
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
    public void updateAssignmentTitle(Assignment assignment, String newTitle) {
        assignment.setTitle(newTitle);
    }

    @Override
    public void updateMaxScore(Assignment assignment, int score) {
        assignment.setMaxScore(score);
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
