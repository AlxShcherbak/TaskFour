package com.ua.dao.interfaces;

import com.ua.classes.Faculty;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 */
public interface AccountDao {
    User getUserById(int id);

    User getUserByIDFull(int id);

    boolean userIsExistById(int id);

    User getUserByLogin(String login);

    User getUserByLoginFull(String login);

    boolean userIsExistByLogin(String login);

    boolean addNewUser(User user);

    List<Student> getStudentsByFaculty(int id);

    List<Student> getStudentsByFaculty(Faculty faculty);

    Teacher getTeacherByFaculty(int id);

    Teacher getTeacherByFaculty(Faculty faculty);

    boolean goToFacylty(int idStudent, int idFacult);
}
