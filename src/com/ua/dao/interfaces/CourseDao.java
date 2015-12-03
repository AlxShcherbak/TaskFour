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
    Course getCourseById(int id);

    List<Course> getAllCourses();
}
