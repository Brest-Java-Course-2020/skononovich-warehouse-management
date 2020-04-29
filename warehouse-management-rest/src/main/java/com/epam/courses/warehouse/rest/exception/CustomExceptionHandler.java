package com.epam.courses.warehouse.rest.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String PRODUCT_NOT_FOUND = "product.not_found";
    public static final String PRODUCT_IS_EXIST = "product.is_exist";
    public static final String VALIDATION_ERROR = "validation_error";
    public static final String PRODUCT_NOT_ENOUGH = "product.not_enough";

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIsExistException.class)
    public final ResponseEntity<ErrorResponse> handleProductIsExistException(
            ProductIsExistException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_IS_EXIST, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotEnoughException.class)
    public final ResponseEntity<ErrorResponse> handleProductNotEnoughException(
            ProductNotEnoughException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_NOT_ENOUGH, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(value = {IllegalArgumentException.class, DataIntegrityViolationException.class, HttpMessageConversionException.class})
    public ResponseEntity<ErrorResponse> handleAnotherExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(VALIDATION_ERROR, ex), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
