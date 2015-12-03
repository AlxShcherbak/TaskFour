/*
 * Alx Shcherbak
 */

package com.ua.servlets.filters;

import com.ua.dao.DAOFactory.DAOFactory;
import com.ua.dao.DAOFactory.DataSourceConnPoolFactory;
import com.ua.servlets.Dispatcher;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebFilter(filterName = "FilterRegistration")
public class FilterRegistration implements Filter {
    static Logger logger = Logger.getLogger(FilterRegistration.class.toString());
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String name = req.getParameter("name");

        if (login == null || password == null || password2 == null || name == null ||
                login.equals("") || password.equals("") || password2.equals("") || name.equals("")) {
            req.setAttribute("RegMistake", "one of field don't wrote");
            Dispatcher.dispatch((HttpServletRequest) req, (HttpServletResponse) resp, "pages/registration");
        } else if (!password.equals(password2)) {
            req.setAttribute("RegMistake", "passwords not equals");
            Dispatcher.dispatch((HttpServletRequest) req, (HttpServletResponse) resp, "pages/registration");
        } else if (daoFactory.getAccountDAO().userIsExistByLogin(login)) {
            req.setAttribute("RegMistake", "user with this login already exist");
            Dispatcher.dispatch((HttpServletRequest) req, (HttpServletResponse) resp, "pages/registration");
        } else {
            req.setAttribute("RegMistake", "");
            logger.info("RegistrationFilter : reg" + login);
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
