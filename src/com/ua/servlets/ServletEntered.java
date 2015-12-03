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
 */
@WebServlet(name = "ServletEntered")
public class ServletEntered extends HttpServlet {
    static Logger logger = Logger.getLogger(ServletEntered.class.toString());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean access = false;
        String login = null;
        String role = null;

        if (session.getAttribute("access") != null && session.getAttribute("login") != null &&
                session.getAttribute("role") != null) {
            login = (String) session.getAttribute("login");
            access = Boolean.valueOf((String) session.getAttribute("access"));
            role = (String) session.getAttribute("role");
        }
        String page = "/indexEnter";

        if (access) {
            page = "/login";
            if (role.toLowerCase().equals("student".toLowerCase())) {
                logger.info("admin user in : USER - " + login);
            } else {
                if (role.toLowerCase().equals("teacher".toLowerCase())) {
                    logger.info("admin user in : USER - " + login);
                }
            }
        } else {
            logger.info("guest user in");
            session.setAttribute("LoginFould", "");
            Dispatcher.dispatch(request, response, "pages/indexGuest");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
