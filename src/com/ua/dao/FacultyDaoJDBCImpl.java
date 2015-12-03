/*
 * Alx Shcherbak
 */

package com.ua.dao;

import com.ua.classes.Faculty;
import com.ua.classes.Mark;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.enums.StatusFaculty;
import com.ua.classes.factories.UserFactory;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.interfaces.FacultyDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public class FacultyDaoJDBCImpl extends AbstractDaoImpl implements FacultyDao {
    public FacultyDaoJDBCImpl(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Faculty getFacultyByID(int id) {
        Faculty faculty = null;
        try {
            faculty = find("SELECT * FROM `FACULTY` WHERE FACULTY.ID =" + id,
                    set -> set != null ? new Faculty(set.getInt("ID"),
                            daoFactory.getCourseDao().getCourseById(set.getInt("COURSE")),
                            StatusFaculty.valueOf(set.getString("STATUS").toUpperCase())) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public Faculty getFacultyByIDFull(int id) {
        Faculty faculty = null;
        try {
            faculty = find("SELECT * FROM `FACULTY` WHERE FACULTY.ID =" + id,
                    set -> set != null ? new Faculty(set.getInt("ID"),
                            daoFactory.getCourseDao().getCourseById(set.getInt("COURSE")),
                            StatusFaculty.valueOf(set.getString("STATUS").toUpperCase())) : null);
            faculty.setTeacher(daoFactory.getAccountDAO().getTeacherByFaculty(faculty));
            faculty.setStudentList(daoFactory.getAccountDAO().getStudentsByFaculty(faculty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public List<Faculty> getAllFacultyes() {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY`";
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                Faculty f = new Faculty(functionConn.getResultSet().getInt("ID"),
                        daoFactory.getCourseDao().getCourseById(functionConn.getResultSet().getInt("COURSE")),
                        StatusFaculty.valueOf(functionConn.getResultSet().getString("STATUS").toUpperCase()));
                f.setTeacher(daoFactory.getAccountDAO().getTeacherByFaculty(f));
                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public List<Faculty> getFacultyListByTeacherID(int teacherID) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY` WHERE TEACHER = " + teacherID;
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                Faculty f = new Faculty(functionConn.getResultSet().getInt("ID"),
                        daoFactory.getCourseDao().getCourseById(functionConn.getResultSet().getInt("COURSE")),
                        (Teacher) daoFactory.getAccountDAO().getUserById(functionConn.getResultSet().getInt("TEACHER")),
                        StatusFaculty.valueOf(functionConn.getResultSet().getString("STATUS").toUpperCase()));

                f.setStudentList(daoFactory.getAccountDAO().getStudentsByFaculty(f));
                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public List<Faculty> getFacultyListByStudentID(int studentID) {
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();

        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE STUDENT = " + studentID;
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                returnValue.add(getFacultyByIDWithTeacher(functionConn.getResultSet().getInt("FACULTY")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public List<Faculty> getFacultyListByTeacher(Teacher teacher) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM FACULTY WHERE TEACHER = " + teacher.getId();
        Faculty f;
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                f = new Faculty(functionConn.getResultSet().getInt("ID"),
                        daoFactory.getCourseDao().getCourseById(functionConn.getResultSet().getInt("COURSE")),
                        teacher,
                        StatusFaculty.valueOf(functionConn.getResultSet().getString("STATUS").toUpperCase()));

                f.setStudentList(daoFactory.getAccountDAO().getStudentsByFaculty(f));
                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public List<Faculty> getFacultyListByStudent(Student student) {
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();

        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE STUDENT = " + student.getId();
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                returnValue.add(getFacultyByIDWithTeacher(functionConn.getResultSet().getInt("FACULTY")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public Faculty getFacultyByIDWithTeacher(int id) {
        Faculty faculty = null;
        try {
            faculty = find("SELECT * FROM `FACULTY` WHERE ID =" + id,
                    set -> set != null ? new Faculty(set.getInt("ID"),
                            daoFactory.getCourseDao().getCourseById(set.getInt("COURSE")),
                            (Teacher) daoFactory.getAccountDAO().getUserById(set.getInt("TEACHER")),
                            StatusFaculty.valueOf(set.getString("STATUS").toUpperCase())) : null);
            if (faculty != null)
                faculty.setTeacher(daoFactory.getAccountDAO().getTeacherByFaculty(faculty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public Faculty getFacultyByIDWithStudents(int id) {
        Faculty faculty = null;
        try {
            faculty = find("SELECT * FROM `FACULTY` WHERE ID =" + id,
                    set -> set != null ? new Faculty(set.getInt("ID"),
                            daoFactory.getCourseDao().getCourseById(set.getInt("COURSE")),
                            (Teacher) daoFactory.getAccountDAO().getUserById(set.getInt("TEACHER")),
                            StatusFaculty.valueOf(set.getString("STATUS").toUpperCase())) : null);
            if (faculty != null)
                faculty.setStudentList(daoFactory.getAccountDAO().getStudentsByFaculty(faculty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public List<Faculty> getAllFacultyesWithAll() {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY`";
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                Faculty f = new Faculty(functionConn.getResultSet().getInt("ID"),
                        daoFactory.getCourseDao().getCourseById(functionConn.getResultSet().getInt("COURSE")),
                        StatusFaculty.valueOf(functionConn.getResultSet().getString("STATUS").toUpperCase()));

                f.setStudentList(daoFactory.getAccountDAO().getStudentsByFaculty(f));
                f.setTeacher(daoFactory.getAccountDAO().getTeacherByFaculty(f));

                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    @Override
    public boolean changeStatus(int facultyID, StatusFaculty st) {
        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String query = "UPDATE FACULTY SET `STATUS` = '" + st.name() + "' WHERE ID=" + facultyID;
        if (functionConn.updateQuery(query) == 0) {
            functionConn.close();
            return false;
        } else {
            functionConn.close();
            return true;
        }
    }
}
