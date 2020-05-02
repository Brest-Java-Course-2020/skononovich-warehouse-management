package com.epam.courses.warehouse.rest.exception;

import com.epam.courses.warehouse.model.Product;

/**
 *ProductIsExistException. Throw then user try to create exist product.
 */
public final class ProductIsExistException extends Exception {
    /**
     * Constructor for ProductIsExistException.
     * @param product Product.
     */
    public ProductIsExistException(final Product product) {
        super("Product with name " + product.getProductName() + " is exist");
    }
}
