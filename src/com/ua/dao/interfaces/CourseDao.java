/*
 * Alx Shcherbak
 */

package com.ua.dao.interfaces;

import com.ua.classes.Course;

import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public interface CourseDao {
    /**
     * Выборка курса по ид
     *
     * @param id - ид курса
     * @return - курс с ид
     */
    Course getCourseById(int id);

    /**
     * Выборка списка всех курсов в БД
     *
     * @return - список всех курсов
     */
    List<Course> getAllCourses();
}
