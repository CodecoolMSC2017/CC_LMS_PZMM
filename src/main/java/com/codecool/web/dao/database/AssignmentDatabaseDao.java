package com.codecool.web.dao.database;

import com.codecool.web.dao.AssignmentDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.dao.database.AbstractDao;
import com.codecool.web.service.exception.EmptyFieldException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class AssignmentDatabaseDao extends AbstractDao implements AssignmentDao {

    public AssignmentDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Assignment> getAllAssignments() throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                assignments.add(fetchAssignment(resultSet));
            }
        }
        return assignments;
    }

    @Override
    public Assignment addNewAssignment(String title, String question, int maxScore, boolean isPublished) throws SQLException, EmptyFieldException {
        if (question.equals("") || title.equals("")) {
            throw new EmptyFieldException("Title cannot be empty");
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
        if (newTitle.equals("")) {
            throw new EmptyFieldException("Title cannot be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Update assignments Set title = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        if (newQuestion.equals("")) {
            throw new EmptyFieldException("Title cannot be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Update assignments Set question = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
    public void updateIsPublishedById(int id, boolean isPublished) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update assignments set is_published = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
    public void addToSubmittedAssignments(int assignmentId, int userId, String answer) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users_assignments (user_id, assignment_id, answer) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.setInt(2, assignmentId);
            statement.setString(3, answer);
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
    public HashMap<Assignment, String> getSubmittedAssignmentsById(int userId) throws SQLException {
        HashMap<Assignment, String> assignments = new HashMap<>();
        String sql = "SELECT * FROM assignments as ass " +
            "JOIN users_assignments AS ua ON ua.assignment_id = ass.id " +
            "WHERE ua.user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    assignments.put(fetchAssignment(resultSet), resultSet.getString("answer"));
                }
            }
        }
        return assignments;
    }

    @Override
    public List<Assignment> getUnSubmittedAssignmentsById(int userId) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments " +
            "WHERE assignments.id not in " +
            "(SELECT ua.assignment_id " +
            "FROM users_assignments AS ua " +
            "WHERE ua.user_id = ?)";
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
    public Assignment getAssignmentByIdForUser(int userId, int assignmentId) throws SQLException {
        String sql = "SELECT * FROM assignments as ass " +
            "JOIN users_assignments AS ua ON ass.id = ua.assignment_id " +
            "JOIN users AS u ON ua.user_id = u.id " +
            "WHERE ua.user_id = ? AND ua.assignment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, assignmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchAssignment(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public boolean isSubmitted(int userId, int assignmentId) throws SQLException {
        boolean isSubmitted = false;
        String sql = "SELECT * FROM assignments as ass " +
            "JOIN users_assignments AS ua ON ass.id = ua.assignment_id " +
            "JOIN users AS u ON ua.user_id = u.id " +
            "WHERE ua.user_id = ? AND ua.assignment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, assignmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    isSubmitted = true;
                }
            }
        }
        return isSubmitted;
    }

    @Override
    public String getAnswerForAssignmentByUserId(int userId, int assignmentId) throws SQLException {
        String sql = "SELECT answer from users_assignments AS ua " +
            "WHERE ua.user_id = ? AND ua.assignment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, assignmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("answer");
                }

            }
            return null;
        }
    }
}
