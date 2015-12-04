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
 * Робота с таблицей Faculty - факультатив
 *
 * @author AlxEx
 */
public interface FacultyDao {
    /**
     * Выборка факультатива по ИД без списка студентов и преподователя
     *
     * @param id - ид искомого факультатива
     * @return - искомый факультатив или null
     */
    Faculty getFacultyByID(int id);

    /**
     * Выборка факультатива по ИД со списком студентов и преподователем
     *
     * @param id - ид искомого факультатива
     * @return - искомый факультатив или null
     */
    Faculty getFacultyByIDFull(int id);

    /**
     * Выборка всех факультативов с преподователями без списка студентов
     *
     * @return - список факультативов
     */
    List<Faculty> getAllFacultyes();

    /**
     * Выборка факультативов ведомых преподователем с ИД
     *
     * @param teacherID - ид преподоватеоля
     * @return - список ведомых преподователем факультативов
     */
    List<Faculty> getFacultyListByTeacherID(int teacherID);

    /**
     * Выборка посещаемых студентом факультативов
     *
     * @param studentID - ид студента
     * @return - список посещаемых факультативов
     */
    List<Faculty> getFacultyListByStudentID(int studentID);

    /**
     * Выборка факультативов ведомых преподователем
     *
     * @param teacher - преподователь
     * @return - список ведомых преподователем факультативов
     */
    List<Faculty> getFacultyListByTeacher(Teacher teacher);

    /**
     * Выборка посещаемых студентом факультативов
     *
     * @param student - студент
     * @return - список посещаемых факультативов
     */
    List<Faculty> getFacultyListByStudent(Student student);

    /**
     * Выборка факультативов ведомых преподователем с ИД
     *
     * @param id - ид преподоватеоля
     * @return - список ведомых преподователем факультативов
     */
    Faculty getFacultyByIDWithTeacher(int id);

    /**
     * Выборка посещаемых студентом факультативов
     *
     * @param id - ид студента
     * @return - список посещаемых факультативов
     */
    Faculty getFacultyByIDWithStudents(int id);

    /**
     * Полная выборка всех факультативов с преподователями и студентами сущности студентов несвязаны
     *
     * @return
     */
    List<Faculty> getAllFacultyesWithAll();

    /**
     * изменение статуса факультатива с ид
     *
     * @param facultyID - ид факультатива
     * @param st        - новый статус
     * @return - результат выполнения
     */
    boolean changeStatus(int facultyID, StatusFaculty st);
}
