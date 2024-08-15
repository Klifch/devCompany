package com.programming3.devcompany.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleDatabaseException(SQLException ex, Model model) {
        logger.error("Database error occurred: {}", ex.getMessage());

        model.addAttribute("message", ex.getMessage());

        return "custom-errors/database-error";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFoundException(NoResourceFoundException ex, Model model) {
        logger.error("Requested page not found: {}", ex.getMessage());

        model.addAttribute("message", "The page you are looking for does not exist.");

        return "custom-errors/general-error";
    }

}
