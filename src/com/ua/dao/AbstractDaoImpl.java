package com.ua.dao;

import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.mappers.Mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 */
public class AbstractDaoImpl {
    protected DAOFactory daoFactory;

    public AbstractDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    protected <T> T find(String sql, Mapper<T> mapper) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    protected boolean sqlUpdate(String sql) throws SQLException{
        Connection connection = null;
        int resultValue = 0;
        Statement statement = null;
        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultValue = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (resultValue > 0) {
            return true;
        }
        return false;
    }
}
