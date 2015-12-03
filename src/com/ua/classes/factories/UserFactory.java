/*
 * Alx Shcherbak
 */

package com.ua.classes.factories;

import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;
import com.ua.classes.enums.UserRole;


/**
 * Created by AlxEx on 02.12.2015.
 */
public class UserFactory {
    public static User getUser(int id, String name, String login, String password, String role) {
        UserRole roleEN = UserRole.getRole(role);
        switch (roleEN) {
            case ADMIN:
                return new User(id, name, login, password);
            case STUDENT:
                return new Student(id, name, login, password);
            case TEACHER:
                return new Teacher(id, name, login, password);
        }
        throw new IllegalArgumentException();
    }
}
