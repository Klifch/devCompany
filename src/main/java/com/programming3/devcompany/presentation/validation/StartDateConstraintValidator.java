package com.programming3.devcompany.presentation.validation;

import com.programming3.devcompany.presentation.converter.DateToStringConverter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StartDateConstraintValidator implements ConstraintValidator<StartDateValidation, LocalDate> {

    private Logger logger = LoggerFactory.getLogger(StartDateConstraintValidator.class);

    private String startDatePrefix;

    @Override
    public void initialize(StartDateValidation constraintAnnotation) {
        startDatePrefix = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        boolean result;

        if (value != null) {
            logger.info("Annotation StartDateConstraint received value!");
            try {
                logger.info("trying to compare value with provided in annotation prefix...");
                result = value.isAfter(LocalDate.parse(startDatePrefix, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                logger.info("Comparison is successful, value after prefix = {}", result);
            } catch (DateTimeParseException e) {
                logger.warn("Failed to compare, caught exception: {}", e.toString());
                result = true;
            }
        } else {
            logger.warn("No value provided!");
            result = true;
        }

        return result;
    }
}
