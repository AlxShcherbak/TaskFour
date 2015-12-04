/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.classes.Student;
import com.ua.classes.factories.UserFactory;
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
 * регистрация пользователя - новый пользователь всегда студент
 */
@WebServlet(name = "ServletRegistration")
public class ServletRegistration extends HttpServlet {
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();
    static Logger logger = Logger.getLogger(ServletRegistration.class.toString());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        if (daoFactory.getAccountDAO().addNewUser(new Student(name, login, password))) {
            logger.info("ServletRegistration: registration done: login" + login);
            Dispatcher.dispatch(request, response, "pages/indexGuest");
        } else {
            request.setAttribute("RegMistake", "user registration fault");
            Dispatcher.dispatch(request, response, "pages/registration");
        }

        Dispatcher.dispatch(request,response,"index");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
