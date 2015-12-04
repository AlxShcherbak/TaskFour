package com.ua.dao;

import com.ua.classes.Faculty;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;
import com.ua.classes.enums.UserRole;
import com.ua.classes.factories.UserFactory;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;
import com.ua.dao.interfaces.AccountDao;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 * @author AlxEx
 * @see com.ua.dao.interfaces.AccountDao
 * @see com.ua.dao.AbstractDaoImpl
 */
public class AccountDaoJDBCImpl extends AbstractDaoImpl implements AccountDao {

    public AccountDaoJDBCImpl(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public User getUserById(int id) {
        User u = null;
        try {
            u = find("SELECT * FROM ACCOUNT WHERE ACCOUNT.ID =" + id,
                    set -> set != null ? UserFactory.getUser(set.getInt("ID"),
                            set.getString("NAME"),
                            set.getString("LOGIN"),
                            set.getString("PASSWORD"),
                            set.getString("ROLE")) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User getUserByIDFull(int id) {
        User u = null;
        try {
            u = find("SELECT * FROM ACCOUNT WHERE ACCOUNT.ID =" + id,
                    set -> set != null ? UserFactory.getUser(set.getInt("ID"),
                            set.getString("NAME"),
                            set.getString("LOGIN"),
                            set.getString("PASSWORD"),
                            set.getString("ROLE")) : null);
            if (u != null)
                if (u.getClass().equals(Student.class)) {
                    u.setFacultyList(daoFactory.getFacultyDao().getFacultyListByStudent((Student) u));
                } else if (u.getClass().equals(Teacher.class)) {
                    u.setFacultyList(daoFactory.getFacultyDao().getFacultyListByTeacher((Teacher) u));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean userIsExistById(int id) {
        ResultSet resultSet = null;
        try {
            resultSet = find("SELECT * FROM ACCOUNT WHERE ACCOUNT.ID = " + id,
                    set -> set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet != null) return true;
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User u = null;
        try {
            u = find("SELECT * FROM ACCOUNT WHERE LOWER(ACCOUNT.LOGIN) = LOWER('" + login + "')",
                    set -> set != null ? UserFactory.getUser(set.getInt("ID"),
                            set.getString("NAME"),
                            set.getString("LOGIN"),
                            set.getString("PASSWORD"),
                            set.getString("ROLE")) : null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User getUserByLoginFull(String login) {
        User u = null;
        try {
            u = find("SELECT * FROM ACCOUNT WHERE LOWER(ACCOUNT.LOGIN) = LOWER('" + login + "')",
                    set -> set != null ? UserFactory.getUser(set.getInt("ID"),
                            set.getString("NAME"),
                            set.getString("LOGIN"),
                            set.getString("PASSWORD"),
                            set.getString("ROLE")) : null);
            if (u != null)
                if (u.getClass().equals(Student.class)) {
                    u.setFacultyList(daoFactory.getFacultyDao().getFacultyListByStudent((Student) u));
                    ((Student) u).setMarkList(daoFactory.getMarkDao().getMarksByStudent((Student) u));
                } else if (u.getClass().equals(Teacher.class)) {
                    u.setFacultyList(daoFactory.getFacultyDao().getFacultyListByTeacher((Teacher) u));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public boolean userIsExistByLogin(String login) {
        ResultSet resultSet = null;
        try {
            resultSet = find("SELECT * FROM ACCOUNT WHERE LOWER(ACCOUNT.LOGIN) = LOWER('" + login + "')",
                    set -> set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet != null) return true;
        return false;
    }

    @Override
    public boolean addNewUser(User user) {
        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String role = UserRole.STUDENT.name();
        String query = "INSERT INTO ACCOUNT (NAME, LOGIN, PASSWORD, ROLE) VALUES ('" + user.getName() + "','" +
                user.getLogin() + "','" + user.getPassword() + "','" + role + "')";
        if (functionConn.updateQuery(query) == 0) {
            functionConn.close();
            return false;
        } else {
            functionConn.close();
            return true;
        }
    }

//    @Override
//    public List<User> getAllUsers() {
//        FunctionConn functionConn = new FunctionConn();
//        ArrayList<User> returnValue = new ArrayList<User>();
//        functionConn.connect(daoFactory);
//        String query = "SELECT * FROM ACCOUNT";
//        functionConn.executeQuery(query);
//        try {
//            while (functionConn.getResultSet().next()) {
//                User u = UserFactory.getUser(functionConn.getResultSet().getInt("ID"),
//                        functionConn.getResultSet().getString("NAME"),
//                        functionConn.getResultSet().getString("LOGIN"),
//                        functionConn.getResultSet().getString("PASSWORD"),
//                        functionConn.getResultSet().getString("ROLE"));
//                returnValue.add(u);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            functionConn.close();
//        }
//        return returnValue;
//    }

    @Override
    public List<Student> getStudentsByFaculty(int id) {
        List<Student> userList = new ArrayList<>();

        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE FACULTY = " + id;
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                Student st = (Student) daoFactory.getAccountDAO().getUserById(functionConn.
                        getResultSet().getInt("STUDENT"));
                userList.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<Student> getStudentsByFaculty(Faculty faculty) {
        List<Student> userList = new ArrayList<>();

        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(daoFactory);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE FACULTY = " + faculty.getId();
        functionConn.executeQuery(query);
        try {
            while (functionConn.getResultSet() != null && functionConn.getResultSet().next()) {
                Student st = (Student) daoFactory.getAccountDAO().getUserById(functionConn.
                        getResultSet().getInt("STUDENT"));
                st.addMark(daoFactory.getMarkDao().markByStudentFaculty(st, faculty));
                userList.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Teacher getTeacherByFaculty(int id) {
        int teacherID = 0;
        try {
            teacherID = find("SELECT * FROM FACULTY WHERE FACULTY.ID =" + id,
                    set -> set != null ? set.getInt("TEACHER") : -1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Teacher) getUserById(teacherID);
    }

    @Override
    public Teacher getTeacherByFaculty(Faculty faculty) {
        int teacherID = 0;
        try {
            teacherID = find("SELECT * FROM FACULTY WHERE FACULTY.ID =" + faculty.getId(),
                    set -> set != null ? set.getInt("TEACHER") : -1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Teacher) getUserById(teacherID);
    }

    @Override
    public boolean goToFacylty(int idStudent, int idFacult) {
        try {
            return sqlUpdate("INSERT INTO `FACULTY-STUDENT` (STUDENT, FACULTY) VALUES ('" + idStudent + "','" + idFacult + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
