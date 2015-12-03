/*
 * Alx Shcherbak
 */

package com.ua.classes;

/**
 * Created by AlxEx on 02.12.2015.
 * �������� ������, ��������
 */
public class Course {
    /**
     * ������������� � ���� ������
     */
    protected int id;
    /**
     * �������� �����
     */
    protected String title;
    /**
     * �������� ������� ������
     */
    protected String kaf;
    /**
     * �������� �����
     */
    protected String desc;

    public Course() {
    }

    public Course(String title, String kaf, String desc) {
        this.title = title;
        this.kaf = kaf;
        this.desc = desc;
    }

    public Course(int id, String title, String kaf, String desc) {

        this.id = id;
        this.title = title;
        this.kaf = kaf;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getKaf() {
        return kaf;
    }

    public String getDesc() {
        return desc;
    }
}
