package com.codecool.web.dao.database;

import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.dao.database.CurriculumDatabaseDao;
import com.codecool.web.model.Curriculum;
import com.codecool.web.service.exception.EmptyFieldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurriculumDatabaseDaoTest {

    private String user=System.getProperty("user");
    private String password = System.getProperty("password");
    private String database = System.getProperty("database");
    private String url = "jdbc:postgresql://localhost/" + database + "?user=" + user + "&password=" + password;
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    CurriculumDao currDao = new CurriculumDatabaseDao(connection);

    @BeforeEach
    void setUp() throws SQLException {
        String sql = "DROP TABLE IF EXISTS curriculums;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        String sqlinit = "CREATE TABLE public.curriculums (\n" +
            "    id serial PRIMARY KEY,\n" +
            "    title text NOT NULL,\n" +
            "    content text NOT NULL,\n" +
            "    is_published boolean NOT NULL\n" +
            ");";
        statement.executeUpdate(sqlinit);
    }

    @Test
    void getAllCurriculums() throws SQLException, EmptyFieldException {
        assertEquals(currDao.getAllCurriculums().size(),0);

        currDao.addNewCurriculum("Kisbaba","Wühühühü",true);
        currDao.addNewCurriculum("Metzkehviki","Mútkó",true);
        currDao.addNewCurriculum("DoktohAlbá","It's my life",true);

        assertEquals(currDao.getAllCurriculums().size(),3);

    }

    @Test
    void addNewCurriculum() throws SQLException, EmptyFieldException {
        List<Curriculum> curriculumList = currDao.getAllCurriculums();
        int listLength = curriculumList.size();
        currDao.addNewCurriculum("Kisbaba","Wühühü",true);
        assertEquals(listLength + 1,currDao.getAllCurriculums().size());

    }


    @Test
    void getCurriculumById() throws SQLException, EmptyFieldException {
        Curriculum curr = new Curriculum(1,"Kisbaba","Wühühü",true);
        currDao.addNewCurriculum("Kisbaba","Wühühü",true);

        assertEquals(curr.getTitle(),currDao.getCurriculumById(1).getTitle());

        assertEquals("Wühühü",currDao.getCurriculumById(1).getContent());
    }

    @Test
    void updateCurriculumTitle() throws SQLException, EmptyFieldException {
        currDao.addNewCurriculum("Kisbaba","Wühühü",true);
        currDao.updateCurriculumTitle(currDao.getCurriculumById(1),"Nagybaba");

        assertEquals("Nagybaba",currDao.getCurriculumById(1).getTitle());

    }

    @Test
    void updateContent() throws SQLException, EmptyFieldException {
        currDao.addNewCurriculum("Kisbaba","Wühühü",true);
        assertEquals("Wühühü",currDao.getCurriculumById(1).getContent());
        currDao.updateContent(currDao.getCurriculumById(1),"Bömellény");
        assertEquals("Bömellény",currDao.getCurriculumById(1).getContent());
    }

    @Test
    void updateIsPublished() throws SQLException, EmptyFieldException {
        currDao.addNewCurriculum("Kisbaba","Wühühü",true);
        currDao.updateIsPublished(currDao.getCurriculumById(1),false);
        assertEquals(false, currDao.getCurriculumById(1).isPublished());
    }
}
