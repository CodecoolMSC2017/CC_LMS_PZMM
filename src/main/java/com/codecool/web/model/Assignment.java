package com.codecool.web.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Assignment {
    private HashMap<String, String> assignment;
    private String title;
    private int maxScore;
    private boolean isDone;
    private Date dueDate;

    public Assignment(HashMap<String, String> assignment, String title, int maxScore ,boolean isDone, Date dueDate) {
        this.assignment = assignment;
        this.title = title;
        this.maxScore = maxScore;
        this.isDone = isDone;
        this.dueDate = dueDate;
    }

    public HashMap<String, String> getAssignment() {
        return assignment;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean isDone() {
        return isDone;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setAssignment(HashMap<String, String> assignment) {
        this.assignment = assignment;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDueDate(Date dueDate) {

        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Assignment(" + "assignment: " + assignment + ", title: " + title +
            ", Maximum score: " + maxScore +
            ", isDone=" + isDone +
            ", dueDate=" + dueDate +
            ')';
    }
}
