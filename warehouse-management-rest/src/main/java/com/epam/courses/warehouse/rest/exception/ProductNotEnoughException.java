package com.epam.courses.warehouse.rest.exception;

public class ProductNotEnoughException extends Exception {
    public ProductNotEnoughException(Integer id){
        super("Product not enough for id:" + id);
    }
}
