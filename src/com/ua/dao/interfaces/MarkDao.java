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
 * Интерфейс работы с таблицей оценок
 */
public interface MarkDao {
    /**
     * Выборка оценки по ид студента с связаным факультативом
     * @param id - ид студента
     * @return - лист оценок студента
     */
    List<Mark> getMarksByStudentId(int id);

    /**
     *Добавление в БД новой оценки
     * @param mark - записуемая оценка
     * @return - результат выполнения
     */
    boolean addMark(Mark mark);

    /**
     * Выборка оценки по студенту с связаным факультативом
     * @param student -  студент
     * @return - лист оценок студента
     */
    List<Mark> getMarksByStudent(Student student);

    /**
     * Выборка оценки через ид студента и факультатива
     * @param student - ид студента
     * @param faculty - ид факультатива
     * @return - оценка
     */
    Mark markByStudentFaculty(int student, int faculty);

    /**
     * Выборка оценки через студента и факультатива
     * @param student - студент
     * @param faculty - факультатив
     * @return - оценка
     */
    Mark markByStudentFaculty(Student student, Faculty faculty);
}
