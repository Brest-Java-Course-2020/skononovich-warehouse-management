package com.epam.courses.warehouse.rest.exception;

import com.epam.courses.warehouse.model.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id){
        super("Product not found for id:" + id);
    }
}
