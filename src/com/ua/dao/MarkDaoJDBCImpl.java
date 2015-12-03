/*
 * Alx Shcherbak
 */

package com.ua.dao;

import com.ua.classes.*;
import com.ua.classes.factories.UserFactory;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.interfaces.MarkDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public class MarkDaoJDBCImpl extends AbstractDaoImpl implements MarkDao {
    public MarkDaoJDBCImpl(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public List<Mark> getMarksByStudentId(int id) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Mark> returnValue = new ArrayList<Mark>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM MARK WHERE STUDENT = " + id;
        functionConn.executeQuery(query);
        Student st = (Student) daoFactory.getAccountDAO().getUserById(id);
        try {
            while (functionConn.getResultSet()!=null && functionConn.getResultSet().next()) {
                returnValue.add(new Mark(daoFactory.getFacultyDao().getFacultyByID(functionConn.getResultSet().getInt("FACULTY")),
                        st,
                        functionConn.getResultSet().getString("MARK")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public boolean addMark(Mark mark) {
        try {
            return sqlUpdate("INSERT INTO MARK (STUDENT, FACULTY, MARK) VALUES ('" + mark.getStudent().getId() + "','" +
                    mark.getFaculty().getId() + "','" + mark.getMark() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Mark> getMarksByStudent(Student student) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Mark> returnValue = new ArrayList<Mark>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM MARK WHERE STUDENT = " + student.getId();
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet()!=null && functionConn.getResultSet().next()) {
                returnValue.add(new Mark(daoFactory.getFacultyDao().getFacultyByID(functionConn.getResultSet().getInt("FACULTY")),
                        student,
                        functionConn.getResultSet().getString("MARK")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public Mark markByStudentFaculty(int student, int faculty) {
        Student st = (Student) daoFactory.getAccountDAO().getUserById(student);
        Faculty f = daoFactory.getFacultyDao().getFacultyByID(faculty);
        try {
            return find("SELECT * FROM MARK WHERE STUDENT = " + student + " AND FACULTY = " + faculty,
                    set -> set != null ? new Mark(f, st, set.getString("MARK")) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Mark markByStudentFaculty(Student student, Faculty faculty) {
        try {
            return find("SELECT * FROM MARK WHERE STUDENT = " + student.getId() + " AND FACULTY = " + faculty.getId(),
                    set -> set != null ? new Mark(faculty, student, set.getString("MARK")) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
