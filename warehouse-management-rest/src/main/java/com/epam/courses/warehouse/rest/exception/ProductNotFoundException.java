package com.epam.courses.warehouse.rest.exception;

/**
 * ProductNotFoundException.
 * Exception thrown when user try take non exist Product.
 */
public final class ProductNotFoundException extends RuntimeException {

    /**
     * Constructor for ProductNotFoundException.
     * @param id product id.
     */
    public ProductNotFoundException(final Integer id) {
        super("Product not found for id:" + id);
    }
}
