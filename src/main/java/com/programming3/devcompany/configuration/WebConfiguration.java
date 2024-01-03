package com.programming3.devcompany.configuration;

import com.programming3.devcompany.presentation.converter.LocalDateConverter;
import com.programming3.devcompany.presentation.converter.PositionConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer {


    // formatter for conversion String -> LocalDate
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter());
        registry.addConverter(new PositionConverter());
    }
}
