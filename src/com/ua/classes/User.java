package com.ua.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 * абстрактный пользователь
 */
public class User {
    /**
     * идентификатор
     */
    protected int id;
    /**
     * имя пользователя
     */
    protected String name;
    /**
     * логин пользователя
     */
    protected String login;
    /**
     * пароль
     */
    protected String password;
    /**
     * список посещаемых факультативов для студента или ведомых для преподавателя
     */
    protected List<Faculty> facultyList = new ArrayList<>();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login, String password, List<Faculty> facultyList) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.facultyList = facultyList;
    }

    public User(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String password, List<Faculty> facultyList) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.facultyList = facultyList;
    }

    public void addFaculty(Faculty faculty) {
        this.facultyList.add(faculty);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
