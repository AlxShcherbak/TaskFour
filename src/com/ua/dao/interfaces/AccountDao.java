package com.ua.dao.interfaces;

import com.ua.classes.Faculty;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alx Shcherbak
 * Date: 02.12.2015
 * Интерфейс работы с таблицей USER пользователь
 *
 * @author AlxEx
 * @see User
 * @see Teacher
 * @see Student
 * @see Faculty
 */
public interface AccountDao {
    /**
     * Выборка пользователя по ИД без связаных с ним факультативов
     *
     * @param id - идентификатор искомого пользователя
     * @return - пользователь с икомым ИД или null
     */
    User getUserById(int id);

    /**
     * Выборка пользователя по ИД с связаными с ним факультативами
     *
     * @param id - идентификатор искомого пользователя
     * @return - пользователь с икомым ИД или null
     */
    User getUserByIDFull(int id);

    /**
     * проверка существования в базе пользователя с введенным ИД
     *
     * @param id - идентификатор искомого пользователя
     * @return true - существует
     * false - отсутствует
     */
    boolean userIsExistById(int id);

    /**
     * Выборка пользователя по логину без связаных с ним факультативами
     *
     * @param login - логин искомого пользователя
     * @return - пользователь с икомым ИД или null
     */
    User getUserByLogin(String login);

    /**
     * Выборка пользователя по логину с связаными с ним факультативами
     *
     * @param login - логин искомого пользователя
     * @return - пользователь с икомым ИД или null
     */
    User getUserByLoginFull(String login);

    /**
     * проверка существования в базе пользователя с введенным логином
     *
     * @param login - логин искомого пользователя
     * @return true - существует
     * false - отсутствует
     */
    boolean userIsExistByLogin(String login);

    /**
     * Доьавление нового пользователя - студента
     *
     * @param user - пользователь для добавление в базу
     * @return - результат выполнения
     */
    boolean addNewUser(User user);

    /**
     * Выборка списка студентов с оценками которые посещают факультатив
     *
     * @param id - ид факультатива
     * @return - список студентов которые посещают факультатив с ИД
     */
    List<Student> getStudentsByFaculty(int id);

    /**
     * Выборка списка студентов с оценками которые посещают факультатив
     *
     * @param faculty - факультатива
     * @return - список студентов которые посещают факультатив
     */
    List<Student> getStudentsByFaculty(Faculty faculty);

    /**
     * Выборка преподователя без списка ведомых факультативов ведущего факультатив
     *
     * @param id - ид факультатива
     * @return - учитель ведущий факультатив
     */
    Teacher getTeacherByFaculty(int id);

    /**
     * Выборка преподователя без списка ведомых факультативов ведущего факультатив
     *
     * @param faculty - факультатива
     * @return - учитель ведущий факультатив
     */
    Teacher getTeacherByFaculty(Faculty faculty);

    /**
     * Записать студента на факультатив
     *
     * @param idStudent - ид студента
     * @param idFacult  - ид факультатива
     * @return - результат выполнения метода
     */
    boolean goToFacylty(int idStudent, int idFacult);
}
