package com.programming3.devcompany.presentation.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateToStringConverter implements Converter<LocalDate, String> {

    private Logger logger = LoggerFactory.getLogger(DateToStringConverter.class);

    @Override
    public String convert(LocalDate date) {
        logger.info("Converting LocalDate Value to String");
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
