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
@WebFilter(filterName = "FilterLogIn")
public class FilterLogIn implements Filter {
    static Logger logger = Logger.getLogger(FilterLogIn.class.toString());
    DAOFactory daoFactory = DataSourceConnPoolFactory.getDAOFactory();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null || login.equals("") || password.equals("")) {
            req.setAttribute("LoginFould", "Write login/password");
            Dispatcher.dispatch((HttpServletRequest) req, (HttpServletResponse) resp, "pages/indexGuest");
        } else if (!daoFactory.getAccountDAO().userIsExistByLogin(login)) {
            req.setAttribute("LoginFould", "wrong login");
            Dispatcher.dispatch((HttpServletRequest) req, (HttpServletResponse) resp, "pages/indexGuest");
        } else {
            logger.info("LoginFilter pass : login" + login);
            req.setAttribute("LoginFould", "");
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
