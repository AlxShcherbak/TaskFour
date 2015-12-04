/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.classes.User;
import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by AlxEx on 03.12.2015.
 * Начальный сервлет инит работу с сервисом
 */
@WebServlet(name = "ServletEntered")
public class ServletEntered extends HttpServlet {
    /**
     * логер Log4j
     */
    static Logger logger = Logger.getLogger(ServletEntered.class.toString());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean access = false;
        String login = null;
        String role = null;

        // проверка на существование полей логинизации в сессии session.attribute - login
        if (session.getAttribute("access") != null && session.getAttribute("login") != null &&
                session.getAttribute("role") != null) {
            login = (String) session.getAttribute("login");
            access = Boolean.valueOf((String) session.getAttribute("access"));
            role = (String) session.getAttribute("role");
        }
        String page = "/indexEnter";


        // проверка разрешения вхождения на сервис
        if (access) {
            page = "/login"; //сервлет на отработку
            // определение роли пользователя
            if (role.toLowerCase().equals("student".toLowerCase())) { // пользователь студент
                logger.info("admin user in : USER - " + login);
            } else {
                if (role.toLowerCase().equals("teacher".toLowerCase())) { // пользователь преподователь
                    logger.info("admin user in : USER - " + login);
                }
            }
        } else {
            logger.info("guest user in"); // пользователь гость - логинизация небыла провелена
            session.setAttribute("LoginFould", "");
            Dispatcher.dispatch(request, response, "pages/indexGuest");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page); // переадресация на сервлет с адресом page
        dispatcher.forward(request, response);
    }
}
