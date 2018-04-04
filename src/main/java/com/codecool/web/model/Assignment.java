package com.codecool.web.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Assignment {
    private String question;
    private String answer;
    private String title;
    private int maxScore;
    private boolean isDone;
    private boolean isPublished;

    public Assignment(String question, String title, int maxScore, boolean isDone, boolean isPublished) {
        this.question = question;
        this.title = title;
        this.maxScore = maxScore;
        this.isDone = isDone;
        this.isPublished = isPublished;
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
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

    public boolean isPublished() {
        return isPublished;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setDone(boolean done) {
        isDone = done;
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
            ", isDone=" + isDone +
            ", isPublished=" + isPublished +
            '}';
    }
}
