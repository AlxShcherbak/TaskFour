/*
 * Alx Shcherbak
 */

package com.ua.classes.enums;

import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;

/**
 * Created by AlxEx on 02.12.2015.
 */
public enum UserRole {
    TEACHER, STUDENT, ADMIN;

    public static UserRole getRole(String roleString) {
        return UserRole.valueOf(roleString.toUpperCase());
    }

    public static UserRole getRole(User user) {

        if (user.getClass().equals(Student.class)) {
            return STUDENT;
        } else if (user.getClass().equals(Teacher.class)) {
            return TEACHER;
        }
        return null;
    }
}
