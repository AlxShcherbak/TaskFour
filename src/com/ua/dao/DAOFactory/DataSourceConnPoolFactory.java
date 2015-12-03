/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.dao.DAOFactory;

import com.ua.dao.AccountDaoJDBCImpl;
import com.ua.dao.CourseDaoJDBCImpl;
import com.ua.dao.FacultyDaoJDBCImpl;
import com.ua.dao.MarkDaoJDBCImpl;
import com.ua.dao.interfaces.AccountDao;
import com.ua.dao.interfaces.CourseDao;
import com.ua.dao.interfaces.FacultyDao;
import com.ua.dao.interfaces.MarkDao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author AlxEx
 * @see com.ua.dao.DAOFactory.DAOFactory
 */
public class DataSourceConnPoolFactory extends DAOFactory {

    @Override
    public Connection getConnection() throws SQLException {
        DataSource ds = null;
        try {
            InitialContext cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/confluence");
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        }
        return ds.getConnection();
        //return Config.getInstance(servletContext).getDataSource();
    }

    @Override
    public AccountDao getAccountDAO() {
        return new AccountDaoJDBCImpl(this);
    }

    @Override
    public CourseDao getCourseDao() {
        return new CourseDaoJDBCImpl(this);
    }

    @Override
    public FacultyDao getFacultyDao() {
        return new FacultyDaoJDBCImpl(this);
    }

    @Override
    public MarkDao getMarkDao() {
        return new MarkDaoJDBCImpl(this);
    }


}
