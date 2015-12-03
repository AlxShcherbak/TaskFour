package com.ua.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alx Shcherbak on 03.12.2015.
 */
public class Dispatcher {
    public static void dispatch(HttpServletRequest request, HttpServletResponse response, String view)
            throws ServletException, IOException {
        String prefix ="/";
        String sufix =".jsp";
        System.out.println(prefix + view + sufix);
        String page = prefix + view + sufix;
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
        return;
    }
}
