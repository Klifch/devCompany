package com.programming3.devcompany.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Controller
public class LocaleController {

    @GetMapping("/changeLocale")
    public String changeLocale(@RequestParam(value = "localeData", required = false) String localeData, HttpServletRequest request) {

        if (!localeData.isBlank()) {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(localeData));
        }
        return "redirect:/";
    }
}
