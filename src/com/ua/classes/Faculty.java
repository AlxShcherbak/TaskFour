/*
 * Alx Shcherbak
 */

package com.ua.classes;

import com.ua.classes.enums.StatusFaculty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 * Отдельный экземпляр курса
 */
public class Faculty {
    /**
     * идентификатор совподает с БД
     */
    protected int id;
    /**
     * Курс проводимый на факультативе
     */
    protected Course course;
    /**
     * преподаватель курса
     */
    protected Teacher teacher;
    /**
     * статус курса
     * Собираеться, идет, закончен
     */
    protected StatusFaculty status;
    /**
     * список студентов на факультативе
     */
    protected List<Student> studentList = new ArrayList<>();

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Faculty(int id, Course course, Teacher teacher, StatusFaculty status) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.status = status;
    }

    public Faculty(int id, Course course, StatusFaculty status) {
        this.id = id;
        this.course = course;
        this.status = status;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Faculty(int id, Course course, Teacher teacher, StatusFaculty status, List<Student> studentList) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.status = status;
        this.studentList = studentList;

    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public StatusFaculty getStatus() {
        return status;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    public void changeStatus(StatusFaculty status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        return id == faculty.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
