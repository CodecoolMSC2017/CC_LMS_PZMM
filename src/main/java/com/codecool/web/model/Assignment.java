package com.codecool.web.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Assignment extends AbstractModel {
    private String question;
    private String title;
    private int maxScore;
    private boolean isPublished;

    public Assignment(int id, String title, String question, int maxScore, boolean isPublished) {
        super(id);
        this.question = question;
        this.title = title;
        this.maxScore = maxScore;
        this.isPublished = isPublished;
    }

    public String getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        return "Assignment{" +
            "question='" + question + '\'' +
            ", title='" + title + '\'' +
            ", maxScore=" + maxScore +
            ", isPublished=" + isPublished +
            '}';
    }
}
