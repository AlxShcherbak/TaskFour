/*
 * Alx Shcherbak
 */

package com.ua.classes;

import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 * сущность преподователя
 */
public class Teacher extends User {
    public Teacher(String name, String login, String password) {
        super(name, login, password);
    }

    public Teacher(String name, String login, String password, List<Faculty> facultyList) {
        super(name, login, password, facultyList);
    }

    public Teacher(int id, String name, String login, String password) {
        super(id, name, login, password);
    }

    public Teacher(int id, String name, String login, String password, List<Faculty> facultyList) {
        super(id, name, login, password, facultyList);
    }

}
