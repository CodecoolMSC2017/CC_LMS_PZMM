package com.codecool.web.dao;

import com.codecool.web.model.Curriculum;
import com.codecool.web.service.exception.EmptyFieldException;

import java.sql.SQLException;
import java.util.List;

public interface CurriculumDao {

    List<Curriculum> getAllCurriculums() throws SQLException;
    Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws SQLException, EmptyFieldException;
    void removeCurriculum(Curriculum curriculum);
    Curriculum getCurriculumByTitle(String title) throws SQLException;
    void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws SQLException, EmptyFieldException;
    void updateContent(Curriculum curriculum, String newContent) throws SQLException, EmptyFieldException;
    void updateIsPublished(Curriculum curriculum, boolean isPublished) throws SQLException;
}
