package com.codecool.web.service;

import com.codecool.web.model.Curriculum;

import java.util.ArrayList;
import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {

    private final List<Curriculum> curriculums = new ArrayList<>();

    @Override
    public List<Curriculum> getAllCurriculums() {
        return curriculums;
    }

    @Override
    public Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws EmptyFieldException {
        if (title.equals("") || content.equals("")) {
            throw new EmptyFieldException();
        }
        Curriculum newCurriculum = new Curriculum(curriculums.size()+1,title, content, isPublished);
        curriculums.add(newCurriculum);
        return newCurriculum;
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
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws EmptyFieldException {
        if (newTitle.equals("")) {
            throw new EmptyFieldException();
        }
        curriculum.setTitle(newTitle);
    }

    @Override
    public void updateContent(Curriculum curriculum, String newContent) throws EmptyFieldException {
        if (newContent.equals("")) {
            throw new EmptyFieldException();
        }
        curriculum.setContent(newContent);
    }

    @Override
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) {
        curriculum.setPublished(isPublished);
    }
}
