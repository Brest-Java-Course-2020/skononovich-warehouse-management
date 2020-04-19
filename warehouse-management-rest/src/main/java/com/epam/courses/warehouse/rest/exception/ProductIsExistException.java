package com.epam.courses.warehouse.rest.exception;

import com.epam.courses.warehouse.model.Product;

public class ProductIsExistException extends Exception {
    public ProductIsExistException(Product product){
        super("Product with name " + product.getProductName() + " is exist");
    }
}
