package com.codecool.web.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Assignment {
    private HashMap<String, List<String>> assignment;
    private String title;
    private boolean isDone;
    private Date dueDate;

    public Assignment(HashMap<String, List<String>> assignment, String title, boolean isDone, Date dueDate) {
        this.assignment = assignment;
        this.title = title;
        this.isDone = isDone;
        this.dueDate = dueDate;
    }

    public HashMap<String, List<String>> getAssignment() {
        return assignment;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setAssignment(HashMap<String, List<String>> assignment) {
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
        return "Assignment(" + "assignment: " + assignment + " title: " + title + '\'' +
            ", isDone=" + isDone +
            ", dueDate=" + dueDate +
            ')';
    }
}