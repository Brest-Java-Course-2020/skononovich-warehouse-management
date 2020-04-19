package com.epam.courses.warehouse.rest.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id){
        super("Product not found for id:" + id);
    }
}
