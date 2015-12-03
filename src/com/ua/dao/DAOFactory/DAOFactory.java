package com.ua.dao.DAOFactory;

import com.ua.dao.interfaces.AccountDao;
import com.ua.dao.interfaces.CourseDao;
import com.ua.dao.interfaces.FacultyDao;
import com.ua.dao.interfaces.MarkDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 * Абстрактная фабрика подключения к БД
 */
public abstract class DAOFactory {
    /**
     * подключение к БД через connectionPool
     * @return
     */
    public static DAOFactory getDAOFactory() {
        return new DataSourceConnPoolFactory();
    }

    /**
     * подключение к БД
     * @return подключение к БД
     * @throws SQLException
     */
    public abstract Connection getConnection() throws SQLException;

    /**
     * Работа с сущностью Пользователя
     * @return
     */
    public abstract AccountDao getAccountDAO();

    /**
     * Работа с сущностью курс
     * @return
     */
    public abstract CourseDao getCourseDao();

    /**
     * работа с сущностью факультатив
     * @return
     */
    public abstract FacultyDao getFacultyDao();

    /**
     * работа с сущностью оценка
     * @return
     */
    public abstract MarkDao getMarkDao();
}
