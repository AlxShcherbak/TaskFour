/*
 * Alx Shcherbak
 */

package com.ua.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 * сущность студента
 */
public class Student extends User {
    /**
     * оценки студента
     */
    protected List<Mark> markList = new ArrayList<>();

    public void addMark(Mark mark) {
        this.markList.add(mark);
    }

    public Student(String name, String login, String password) {
        super(name, login, password);
    }

    public Student(String name, String login, String password, List<Faculty> facultyList) {
        super(name, login, password, facultyList);
    }

    public Student(int id, String name, String login, String password, List<Faculty> facultyList) {
        super(id, name, login, password, facultyList);
    }

    public Student(String name, String login, String password, List<Faculty> facultyList, List<Mark> markList) {
        super(name, login, password, facultyList);
        this.markList = markList;
    }

    public Student(int id, String name, String login, String password, List<Faculty> facultyList, List<Mark> markList) {
        super(id, name, login, password, facultyList);
        this.markList = markList;
    }

    public Student(int id, String name, String login, String password) {
        super(id, name, login, password);
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }

    public Mark getMarkByFaculty(Faculty faculty) {
        for (Mark mark : markList) {
            if (mark.getFaculty().equals(faculty)) return mark;
        }
        return null;
    }

    public String getMarkByFaculty(int faculty) {
        for (Mark mark : markList) {
            if (mark.getFaculty().getId() == faculty) return mark.getMark();
        }
        return "";
    }
}
