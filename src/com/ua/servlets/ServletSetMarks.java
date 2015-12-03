/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.classes.Faculty;
import com.ua.classes.Mark;
import com.ua.classes.Student;
import com.ua.classes.enums.StatusFaculty;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebServlet(name = "ServletSetMarks")
public class ServletSetMarks extends HttpServlet {
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();
    Logger logger = Logger.getLogger(this.getClass().toString());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Faculty faculty = daoFactory.getFacultyDao().getFacultyByIDWithStudents(Integer.valueOf(request.getParameter("faculty")));
        for (Student student : faculty.getStudentList()) {
            String mark = (String) request.getParameter("stud" + student.getId());
            daoFactory.getMarkDao().addMark(new Mark(faculty, student, mark));
        }
        daoFactory.getFacultyDao().changeStatus(faculty.getId(), StatusFaculty.ENDED);
        Dispatcher.dispatch(request,response,"pages/teacher/indexTeacher");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
