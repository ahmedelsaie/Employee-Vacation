package org.employee.utils.timeZone;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.TimeZone;

@Component
public class TimeZoneFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest.getHeader("Time-Zone") != null) {
            var timeZone = TimeZone.getTimeZone(httpRequest.getHeader("Time-Zone"));
            CurrentTimeZone.setCurrentTimeZone(timeZone);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
