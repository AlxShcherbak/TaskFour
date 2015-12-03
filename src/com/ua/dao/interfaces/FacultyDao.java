/*
 * Alx Shcherbak
 */

package com.ua.dao.interfaces;

import com.ua.classes.Faculty;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.enums.StatusFaculty;

import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public interface FacultyDao {
    Faculty getFacultyByID(int id);

    Faculty getFacultyByIDFull(int id);

    List<Faculty> getAllFacultyes();

    List<Faculty> getFacultyListByTeacherID(int teacherID);

    List<Faculty> getFacultyListByStudentID(int studentID);

    List<Faculty> getFacultyListByTeacher(Teacher teacher);

    List<Faculty> getFacultyListByStudent(Student student);

    Faculty getFacultyByIDWithTeacher(int id);

    Faculty getFacultyByIDWithStudents(int id);

    List<Faculty> getAllFacultyesWithAll();

    boolean changeStatus(int facultyID, StatusFaculty st);
}
