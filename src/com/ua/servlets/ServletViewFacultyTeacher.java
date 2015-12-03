/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.classes.Faculty;
import com.ua.classes.Mark;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.enums.StatusFaculty;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebServlet(name = "ServletViewFacultyTeacher")
public class ServletViewFacultyTeacher extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass().toString());
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        int idFaculty = Integer.valueOf(request.getParameter("sub"));
        Faculty faculty = daoFactory.getFacultyDao().getFacultyByIDFull(idFaculty);
        request.setAttribute("faculty", faculty);
        request.setAttribute("students", faculty.getStudentList());

        if (faculty.getStatus().equals(StatusFaculty.ENDED))
            Dispatcher.dispatch(request, response, "/pages/teacher/viewMarks");
        else
            Dispatcher.dispatch(request, response, "pages/teacher/setMarks");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
