package com.codecool.web.service;

import com.codecool.web.model.Curriculum;

import java.sql.SQLException;
import java.util.List;

public interface CurriculumDao {

    public List<Curriculum> getAllCurriculums() throws SQLException;
    public Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws EmptyFieldException, SQLException;
    public void removeCurriculum(Curriculum curriculum);
    public Curriculum getCurriculumByTitle(String title) throws SQLException;
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws EmptyFieldException, SQLException;
    public void updateContent(Curriculum curriculum, String newContent) throws EmptyFieldException, SQLException;
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) throws SQLException;
}
