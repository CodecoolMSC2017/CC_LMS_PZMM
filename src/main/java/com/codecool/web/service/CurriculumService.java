package com.codecool.web.service;

import com.codecool.web.model.Curriculum;
import com.codecool.web.service.exception.EmptyFieldException;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface CurriculumService {
    List<Curriculum> getAllCurriculums() throws SQLException;
    Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws EmptyFieldException, SQLException, ServiceException;
    Curriculum getCurriculumByTitle(String title) throws SQLException;
    void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws EmptyFieldException, SQLException, ServiceException;
    void updateContent(Curriculum curriculum, String newContent) throws EmptyFieldException, SQLException, ServiceException;
    void updateIsPublished(Curriculum curriculum, boolean isPublished) throws SQLException;
}
