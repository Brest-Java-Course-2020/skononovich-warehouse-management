package com.epam.courses.warehouse.web_app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public final String handleNotFound(HttpClientErrorException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

}
