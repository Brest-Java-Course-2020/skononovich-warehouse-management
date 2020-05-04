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

    /**
     * Handler 404 Not Found Exception.
     * @param ex Exception.
     * @param model Model.
     * @return view name.
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public final String handleNotFound(HttpClientErrorException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

}
