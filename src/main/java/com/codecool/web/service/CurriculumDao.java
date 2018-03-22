package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;

import java.util.List;

public interface CurriculumDao {

    public List<Curriculum> getAllCurriculums();
    public void addNewCurriculum(Curriculum newCurriculum);
    public void removeCurriculum(Curriculum curriculum);
    public Curriculum getCurriculumByTitle(String title);
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle);
    public void updateContent(Curriculum curriculum, String newContent);
    public void updateIsPublished(Curriculum curriculum, boolean isPublished);
    public void updateAssignment(Curriculum curriculum, Assignment newAssignment);
}
