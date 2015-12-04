/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlxEx on 03.12.2015.
 * запись студента на факультатив
 */
@WebServlet(name = "ServletGoToFaculty")
public class ServletGoToFaculty extends HttpServlet {
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stID = Integer.valueOf(request.getParameter("student")); // ид  студента
        int fID = Integer.valueOf(request.getParameter("faculty")); // ид факультатива
        daoFactory.getAccountDAO().goToFacylty(stID, fID); // добавление в базу оценки

        String page = "/login";
        RequestDispatcher dispatcher = request.getRequestDispatcher(page); // переход на сервлет по адресу /login
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
