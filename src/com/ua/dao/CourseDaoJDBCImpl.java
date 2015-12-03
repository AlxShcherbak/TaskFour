/*
 * Alx Shcherbak
 */

package com.ua.dao;

import com.ua.classes.Course;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.interfaces.CourseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public class CourseDaoJDBCImpl extends AbstractDaoImpl implements CourseDao {
    public CourseDaoJDBCImpl(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Course getCourseById(int id) {
        Course course = null;
        try {
            course = find("SELECT * FROM COURSE WHERE COURSE.ID =" + id,
                    set -> set != null ? new Course(set.getInt("ID"),
                            set.getString("TITLE"),
                            set.getString("KAFEDRA"),
                            set.getString("DESCRIPTION")) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Course> returnValue = new ArrayList<Course>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM COURSE";
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet()!=null && functionConn.getResultSet().next()) {
                returnValue.add(new Course(functionConn.getResultSet().getInt("ID"),
                        functionConn.getResultSet().getString("TITLE"),
                        functionConn.getResultSet().getString("KAFEDRA"),
                        functionConn.getResultSet().getString("DESCRIPTION")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }
}
