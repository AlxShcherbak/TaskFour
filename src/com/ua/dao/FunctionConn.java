/*
 * Alx Shcherbak
 */

package com.ua.dao;

import com.ua.dao.DAOFactory.DAOFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: Alx Shcherbak
 * Date: 21.06.2015
 * Time: 19:59
 */
public class FunctionConn {
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;

    void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    void executeQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int updateQuery(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    Statement getStatement() {
        return statement;
    }

    void connect(DAOFactory daoFactory) {
        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
