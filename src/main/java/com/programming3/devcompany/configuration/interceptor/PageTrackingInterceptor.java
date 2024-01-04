package com.programming3.devcompany.configuration.interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PageTrackingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);

        @SuppressWarnings("unchecked")
        List<PageHistory> pageHistory = (List<PageHistory>) session.getAttribute("pageHistory");

        if (pageHistory == null) {
            pageHistory = new ArrayList<>();
        }

        pageHistory.add(new PageHistory(request.getRequestURI(), LocalDateTime.now()));

        session.setAttribute("pageHistory", pageHistory);

        return true;
    }
}