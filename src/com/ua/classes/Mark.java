/*
 * Alx Shcherbak
 */

package com.ua.classes;

/**
 * Created by AlxEx on 02.12.2015.
 * сущность оценки
 */
public class Mark {
    protected Faculty faculty;
    protected Student student;
    protected String mark;

    public Mark(Faculty faculty, Student student, String mark) {
        this.faculty = faculty;
        this.student = student;
        this.mark = mark;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getMark() {
        return mark;
    }

    public Student getStudent() {
        return student;
    }
}
