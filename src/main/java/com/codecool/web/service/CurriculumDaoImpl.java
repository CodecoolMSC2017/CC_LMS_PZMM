package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Curriculum;
import com.codecool.web.model.CurriculumList;

import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {

    private CurriculumList cl = CurriculumList.getInstance();
    private List<Curriculum> curriculums = cl.getCurriculums();

    @Override
    public List<Curriculum> getAllCurriculums() {
        return curriculums;
    }

    @Override
    public void addNewCurriculum(Curriculum newCurriculum) {
        curriculums.add(newCurriculum);
    }

    @Override
    public void removeCurriculum(Curriculum curriculum) {
        curriculums.remove(curriculum);
    }

    @Override
    public Curriculum getCurriculumByTitle(String title) {
        for (Curriculum curriculum : curriculums) {
            if (curriculum.getTitle().equals(title)) {
                return curriculum;
            }
        }
        return null;
    }

    @Override
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) {
        curriculum.setTitle(newTitle);
    }

    @Override
    public void updateContent(Curriculum curriculum, String newContent) {
        curriculum.setContent(newContent);
    }

    @Override
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) {
        curriculum.setPublished(isPublished);
    }

    @Override
    public void updateAssignment(Curriculum curriculum, Assignment newAssignment) {
        curriculum.setRelatedAssignment(newAssignment);
    }
}
