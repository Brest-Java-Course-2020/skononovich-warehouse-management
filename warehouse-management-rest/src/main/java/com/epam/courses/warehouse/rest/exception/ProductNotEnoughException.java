package com.epam.courses.warehouse.rest.exception;

/**
 * ProductNotEnoughException.
 * Exception thrown when user try take more Products
 * then there is in warehouse.
 */
public final class ProductNotEnoughException extends Exception {

    /**
     * Constructor for ProductNotEnoughException.
     * @param id product id.
     */
    public ProductNotEnoughException(final Integer id) {
        super("Product not enough for id:" + id);
    }
}
