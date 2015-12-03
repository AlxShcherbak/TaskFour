/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.classes.Faculty;
import com.ua.classes.Student;
import com.ua.classes.Teacher;
import com.ua.classes.User;
import com.ua.classes.enums.StatusFaculty;
import com.ua.classes.enums.UserRole;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass().toString());
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        boolean access = Boolean.valueOf((String) session.getAttribute("access"));

        User loginingUser;

        if (access) {
            loginingUser = daoFactory.getAccountDAO().getUserByLogin((String) session.getAttribute("login"));
        } else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            loginingUser = daoFactory.getAccountDAO().getUserByLogin(login);

            if (loginingUser.getPassword().equals(password)) {
                session.setAttribute("login", login);
                session.setAttribute("access", "true");
                session.setAttribute("role", UserRole.getRole(loginingUser).toString());
                logger.info("user logining : user - " + login);
            }
        }


        if (loginingUser != null && loginingUser.getClass().equals(Student.class)) {
            //Выборка посещаемых и доступных к посещению факультативов
            Student st = (Student) loginingUser;
            List<Faculty> facultyListGoes = daoFactory.getFacultyDao().getFacultyListByStudent(st);
            List<Faculty> facultyListAvailable = new ArrayList<>();
            List<Faculty> fac = daoFactory.getFacultyDao().getAllFacultyes();
            st.setFacultyList(facultyListGoes);
            st.setMarkList(daoFactory.getMarkDao().getMarksByStudent(st));
            for (Faculty faculty : fac) {
                boolean flag = !faculty.getStatus().equals(StatusFaculty.ENDED);
                if (flag)
                    for (Faculty faculty1 : facultyListGoes) {
                        if (faculty.equals(faculty1)) {
                            flag = false;
                            break;
                        }
                    }
                if (flag) facultyListAvailable.add(faculty);
            }

            request.setAttribute("student", st);
            request.setAttribute("availableFacults", facultyListAvailable);
            request.setAttribute("facultyes", facultyListGoes);
            Dispatcher.dispatch(request, response, "pages/student/indexStudent");
        } else if (loginingUser.getClass().equals(Teacher.class)) {
            request.setAttribute("facultyes", daoFactory.getFacultyDao().getFacultyListByTeacher((Teacher) loginingUser));
            Dispatcher.dispatch(request, response, "pages/teacher/indexTeacher");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        boolean access = Boolean.valueOf((String) session.getAttribute("access"));

        User loginingUser = null;

        if (access) {
            loginingUser = daoFactory.getAccountDAO().getUserByLogin((String) session.getAttribute("login"));
        }


        if (loginingUser != null && loginingUser.getClass().equals(Student.class)) {
            //Выборка посещаемых и доступных к посещению факультативов
            Student st = (Student) loginingUser;
            List<Faculty> facultyListGoes = daoFactory.getFacultyDao().getFacultyListByStudent(st);
            List<Faculty> facultyListAvailable = new ArrayList<>();
            List<Faculty> fac = daoFactory.getFacultyDao().getAllFacultyes();
            for (Faculty faculty : fac) {
                boolean flag = !faculty.getStatus().equals(StatusFaculty.ENDED);
                if (flag)
                    for (Faculty faculty1 : facultyListGoes) {
                        if (faculty.equals(faculty1)) {
                            flag = false;
                            break;
                        }
                    }
                if (flag) facultyListAvailable.add(faculty);
            }

            request.setAttribute("student", st);
            request.setAttribute("availableFacults", facultyListAvailable);
            request.setAttribute("facultyes", facultyListGoes);
            Dispatcher.dispatch(request, response, "pages/student/indexStudent");
        } else if (loginingUser.getClass().equals(Teacher.class)) {
            request.setAttribute("facultyes", daoFactory.getFacultyDao().getFacultyListByTeacher((Teacher) loginingUser));
            Dispatcher.dispatch(request, response, "pages/teacher/indexTeacher");
        }
    }
}
