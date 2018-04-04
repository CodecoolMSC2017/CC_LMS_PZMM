package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;

import java.util.List;

public interface CurriculumDao {

    public List<Curriculum> getAllCurriculums();
    public void addNewCurriculum(String title, String content, boolean isPublished) throws EmptyFieldException;
    public void removeCurriculum(Curriculum curriculum);
    public Curriculum getCurriculumByTitle(String title);
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws EmptyFieldException;
    public void updateContent(Curriculum curriculum, String newContent) throws EmptyFieldException;
    public void updateIsPublished(Curriculum curriculum, boolean isPublished);
}
