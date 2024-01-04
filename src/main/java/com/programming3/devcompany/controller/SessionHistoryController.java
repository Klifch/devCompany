package com.programming3.devcompany.controller;

import com.programming3.devcompany.configuration.interceptor.PageHistory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SessionHistoryController {

    @GetMapping("/sessionHistory")
    public String showSessionHistory(Model model, HttpSession httpSession) {

        List<PageHistory> pageHistories = (List<PageHistory>) httpSession.getAttribute("pageHistory");

        model.addAttribute("pageHistories", pageHistories);

        return "history/history";
    }
}
