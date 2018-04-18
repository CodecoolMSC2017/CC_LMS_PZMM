package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class AssignmentDatabaseDao extends AbstractDao implements AssignmentDao {

    public AssignmentDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Assignment> getAllAssignments() throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT id, name, percentage, creator_id FROM coupons";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                assignments.add(fetchAssignment(resultSet));
            }
        }
        return assignments;
    }

    @Override
    public Assignment addNewAssignment(String title, String question, int maxScore, boolean isDone, boolean isPublished) throws SQLException, EmptyFieldException {
        if (question.equals("") || title.equals("")) {
            throw new EmptyFieldException();
        }
        if (maxScore < 1 || maxScore > 100) {
            throw new IllegalArgumentException("Maximum score should be between 1 and 100!");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO assignments (title, question, max_score, is_published) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, title);
            statement.setString(2, question);
            statement.setInt(3, maxScore);
            statement.setBoolean(4, isPublished);
            executeUpdate(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new Assignment(id, title, question, maxScore, isPublished);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void removeAssignmentById(int id) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Delete From assignments where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
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
    public Assignment getAssignmentById(int id) throws SQLException {
        String sql = "Select * From assignments where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchAssignment(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void updateAssignmentTitleById(int id, String newTitle) throws EmptyFieldException, SQLException {
        if (newTitle == null || newTitle.equals("")) {
            throw new EmptyFieldException();
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Update assignments Set title = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, newTitle);
            statement.setInt(2, id);
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
    public void updateAssignmentQuestionById(int id, String newQuestion) throws EmptyFieldException, SQLException {
        if (newQuestion.equals("") || newQuestion == null) {
            throw new EmptyFieldException();
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Update assignments Set question = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, newQuestion);
            statement.setInt(2, id);
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
    public void updateMaxScoreById(int id, int score) throws SQLException {
        if (score < 1 || score > 100) {
            throw new IllegalArgumentException("Maximum score should be between 1 and 100!");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update assignments set max_score=? where id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, score);
            statement.setInt(2, id);
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
    public void updateIsDoneById(int id, boolean isDone) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update assignments set is_done=? where id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setBoolean(1, isDone);
            statement.setInt(2, id);
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
    public void updateIsPublishedById(int id, boolean isPublished) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update assignments set is_done=? where id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setBoolean(1, isPublished);
            statement.setInt(2, id);
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
    public void add(int assignmentId, int userId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users_assignments (user_id, assignment_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, userId);
            statement.setInt(2,assignmentId);
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
    public Assignment fetchAssignment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String question = resultSet.getString("question");
        int maxScore = resultSet.getInt("max_score");
        boolean isPublished = resultSet.getBoolean("is_published");
        return new Assignment(id, title, question, maxScore, isPublished);
    }

    @Override
    public List<Assignment> getSubmittedAssignmentsById(int userId) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments as ass " +
            "JOIN users_assignments AS ua ON ua.assignment_id = ass.id " +
            "WHERE ua.user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    assignments.add(fetchAssignment(resultSet));
                }
            }
        }
        return assignments;
    }

    @Override
    public List<Assignment> getUnSubmittedAssignmentsById(int userId) throws SQLException {
        return null;
    }

    @Override
    public Assignment getAssignmentByUserId(int userId, int assignmentId) {
        return null;
    }

    @Override
    public void addToSubmittedAssignments(int userId, int assignmentId, String answer) {

    }
}
