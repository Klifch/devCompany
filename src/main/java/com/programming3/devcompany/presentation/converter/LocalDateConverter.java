package com.programming3.devcompany.presentation.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    private Logger logger = LoggerFactory.getLogger(LocalDateConverter.class);

    @Override
    public LocalDate convert(String date) {
        logger.info("Converting String {} to LocalDate", date);
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            logger.warn("Failed to parse string {} to LocalDate!", date);
            return null;
        }
//        https://www.javadevjournal.com/spring-mvc/custom-type-convertor-in-spring-mvc/
    }
}
