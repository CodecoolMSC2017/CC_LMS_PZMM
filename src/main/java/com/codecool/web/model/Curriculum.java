package com.codecool.web.model;

public class Curriculum {
    private String title;
    private String content;
    private boolean isPublished;
    private Assignment relatedAssignment;

    public Curriculum(String title, String content, boolean isPublished, Assignment relatedAssignment) {
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.relatedAssignment = relatedAssignment;
    }

    public Curriculum(String title, String content, boolean isPublished) {
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.relatedAssignment = null;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Assignment getRelatedAssignment() {
        return relatedAssignment;
    }

    public void setRelatedAssignment(Assignment relatedAssignment) {
        this.relatedAssignment = relatedAssignment;
    }

    @Override
    public String toString() {
        return "Curriculum(" +
            "title: " + title + " content: " + content + " isPublished: " + isPublished + " relatedAssignment: "
            + relatedAssignment + ')';
    }
}
