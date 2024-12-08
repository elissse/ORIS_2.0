package com.oris.lab_07_filter.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getServletPath().startsWith("/static/") || httpServletRequest.getServletPath().startsWith("/usercheck") || httpServletRequest.getServletPath().startsWith("/login")) {
            filterChain.doFilter(request, response);
        } else {
            if (httpServletRequest.getCookies() != null) {
                Optional<Cookie> cookie =
                        Arrays.stream(httpServletRequest.getCookies())
                                .filter(c -> c.getName().equals("uuid")).findFirst();
                if (cookie.isPresent()) {
                    filterChain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("/login").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/login").forward(request, response);
            }
        }
    }
}
