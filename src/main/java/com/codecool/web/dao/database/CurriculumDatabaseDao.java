package com.codecool.web.dao.database;

import com.codecool.web.dao.CurriculumDao;
import com.codecool.web.model.Curriculum;
import com.codecool.web.service.exception.EmptyFieldException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDatabaseDao extends AbstractDao implements CurriculumDao {
    public CurriculumDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Curriculum> getAllCurriculums() throws SQLException {
        List<Curriculum> curriculums = new ArrayList<>();
        String sql = "SELECT id,title,content,is_published FROM curriculums;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                curriculums.add(fetchCurriculum(resultSet));
            }
        }
        return curriculums;
    }

    @Override
    public Curriculum addNewCurriculum(String title, String content, boolean isPublished) throws EmptyFieldException, SQLException {
        if(title == null || title.equals("") || content == null || content.equals("")){
            throw new EmptyFieldException("Title can't be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO curriculums (title,content,is_published) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setBoolean(3,isPublished);
            executeUpdate(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new Curriculum(id, title, content,isPublished);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void removeCurriculum(Curriculum curriculum) {

    }

    @Override
    public Curriculum getCurriculumById(int id) throws SQLException {
        String sql = "SELECT id,title,content,is_published FROM curriculums WHERE title = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchCurriculum(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void updateCurriculumTitle(Curriculum curriculum, String newTitle) throws EmptyFieldException, SQLException {
        if(newTitle == null || newTitle.equals("")){
            throw new EmptyFieldException("Title can't be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE curriculums SET title = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, newTitle);
            statement.setInt(2, curriculum.getId());
            executeUpdate(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void updateContent(Curriculum curriculum, String newContent) throws EmptyFieldException, SQLException {
        if(newContent == null || newContent.equals("")){
            throw new EmptyFieldException("Title can't be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE curriculums SET content = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, newContent);
            statement.setInt(2, curriculum.getId());
            executeUpdate(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void updateIsPublished(Curriculum curriculum, boolean isPublished) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE curriculums SET is_published = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setBoolean(1, isPublished);
            statement.setInt(2, curriculum.getId());
            executeUpdate(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private Curriculum fetchCurriculum(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        boolean published = resultSet.getBoolean("is_published");
        return new Curriculum(id, title, content,published);
    }
}
