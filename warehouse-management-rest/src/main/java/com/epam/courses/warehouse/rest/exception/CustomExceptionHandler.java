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
public final class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {
    /**
     * Product not found message.
     */
    public static final String PRODUCT_NOT_FOUND = "product.not_found";
    /**
     * Product is exist message.
     */
    public static final String PRODUCT_IS_EXIST = "product.is_exist";
    /**
     * Validation error message.
     */
    public static final String VALIDATION_ERROR = "validation_error";
    /**
     * Product not enough message.
     */
    public static final String PRODUCT_NOT_ENOUGH = "product.not_enough";

    /**
     * Product not found exception handler.
     * @param ex Exception.
     * @param request web request.
     * @return ResponseEntity.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
           final ProductNotFoundException ex, final WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Product is exist exception handler.
     * @param ex Exception.
     * @param request web request.
     * @return ResponseEntity.
     */
    @ExceptionHandler(ProductIsExistException.class)
    public ResponseEntity<ErrorResponse> handleProductIsExistException(
            final ProductIsExistException ex, final WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_IS_EXIST, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Product not enough exception handler.
     * @param ex Exception.
     * @param request web request.
     * @return ResponseEntity.
     */
    @ExceptionHandler(ProductNotEnoughException.class)
    public ResponseEntity<ErrorResponse> handleProductNotEnoughException(
            final ProductNotEnoughException ex, final WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(PRODUCT_NOT_ENOUGH, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    /**
     * Handler for other another exceptions.
     * @param ex Exception.
     * @param request web request.
     * @return ResponseEntity.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class,
            DataIntegrityViolationException.class,
            HttpMessageConversionException.class})
    public ResponseEntity<ErrorResponse> handleAnotherExceptions(
            final Exception ex,
            final WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(VALIDATION_ERROR, ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
