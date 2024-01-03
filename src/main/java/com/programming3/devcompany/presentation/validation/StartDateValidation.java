package com.programming3.devcompany.presentation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Constraint(validatedBy = StartDateConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD}) // can apply on field or method
@Retention(RetentionPolicy.RUNTIME) // process annotation at runtime
public @interface StartDateValidation {

    // default start date
    public String value() default "2000-01-01";

    // default error message
    public String message() default "must be later than 2000-01-01";

    // default groups
    public Class<?>[] groups() default {};

    // default payloads
    public Class<? extends Payload>[] payload() default {}; // this stuff provides custom details about validation failure
    //                                                         like (severity level, error code etc) idk how to use it right now

}











