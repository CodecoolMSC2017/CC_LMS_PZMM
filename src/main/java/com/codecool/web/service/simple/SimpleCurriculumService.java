package com.codecool.web.service.simple;

import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.model.Curriculum;
import com.codecool.web.service.CurriculumService;
import com.codecool.web.service.exception.EmptyFieldException;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleCurriculumService implements CurriculumService {
    private final CurriculumDao curriculumDao;

    public SimpleCurriculumService(CurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
    }
    @Override
    public List<Curriculum> getAllCurriculums() throws SQLException {
        return curriculumDao.getAllCurriculums();
    }

    @Override
    public Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws SQLException, ServiceException {
        try {
            return curriculumDao.addNewCurriculum(title,content,isPublished);
        } catch (EmptyFieldException e) {
            throw new ServiceException("Title shouldn't be an empty field!");
        }
    }

    @Override
    public Curriculum getCurriculumByTitle(String title) throws SQLException {
        return curriculumDao.getCurriculumByTitle(title);
    }

    @Override
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws SQLException, ServiceException {
        try {
            curriculumDao.updateCurriculumTitle(curriculum,newTitle);
        } catch (EmptyFieldException e) {
            throw new ServiceException("Title shouldn't be an empty field!");
        }
    }

    @Override
    public void updateContent(Curriculum curriculum, String newContent) throws SQLException, ServiceException {
        try {
            curriculumDao.updateContent(curriculum,newContent);
        } catch (EmptyFieldException e) {
            throw new ServiceException("Title shouldn't be an empty field!");
        }
    }

    @Override
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) throws SQLException {
        curriculumDao.updateIsPublished(curriculum,isPublished);
    }
}
