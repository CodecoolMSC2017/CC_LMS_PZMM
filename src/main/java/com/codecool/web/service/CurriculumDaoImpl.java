package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;

import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {

    private CurriculumList cl = new CurriculumList();
    private List<Curriculum> curriculums = cl.getInstance();
    
    @Override
    public List<Curriculum> getAllCurriculums() {
        return null;
    }

    @Override
    public void addNewCurriculum(Curriculum newCurriculum) {

    }

    @Override
    public void removeCurriculum(Curriculum curriculum) {

    }

    @Override
    public Curriculum getCurriculumByTitle(String title) {
        return null;
    }

    @Override
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) {

    }

    @Override
    public void updateContent(Curriculum curriculum, String newContent) {

    }

    @Override
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) {

    }

    @Override
    public void updateAssignment(Curriculum curriculum, Assignment newAssignment) {

    }
}
