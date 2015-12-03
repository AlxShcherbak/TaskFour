/*
 * Alx Shcherbak
 */

package com.ua.dao.interfaces;

import com.ua.classes.Faculty;
import com.ua.classes.Mark;
import com.ua.classes.Student;

import java.util.List;

/**
 * Created by AlxEx on 02.12.2015.
 */
public interface MarkDao {
    List<Mark> getMarksByStudentId(int id);

    boolean addMark(Mark mark);

    List<Mark> getMarksByStudent(Student student);

    Mark markByStudentFaculty(int student, int faculty);

    Mark markByStudentFaculty(Student student, Faculty faculty);
}
