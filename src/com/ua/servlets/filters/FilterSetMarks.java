/*
 * Alx Shcherbak
 */

package com.ua.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by AlxEx on 03.12.2015.
 */
@WebFilter(filterName = "FilterSetMarks")
public class FilterSetMarks implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
