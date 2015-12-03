/*
 * Alx Shcherbak
 */

package com.ua.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebServlet(name = "ServletLogOut")
public class ServletLogOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("access");
        session.removeAttribute("login");
        session.removeAttribute("role");
        Dispatcher.dispatch(request, response, "index");
    }
}
