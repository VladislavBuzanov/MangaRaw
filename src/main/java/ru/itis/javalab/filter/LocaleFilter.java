package ru.itis.javalab.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LocaleFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameter("lang") != null) {
            String locale = request.getParameter("lang");
            if (locale.equals("en")) {
                ((HttpServletResponse) response).addCookie(new Cookie("clientLocale", "en"));
                chain.doFilter(request, response);
            } else if (locale.equals("ru")) {
                ((HttpServletResponse) response).addCookie(new Cookie("clientLocale", "ru"));
                chain.doFilter(request, response);
            } else {
                response.getWriter().println("WRONG LANG");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
