package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    /**
     * Create product.
     * @param product product.
     * @return product id.
     */
    Integer create(Product product);

    /**
     * Get list of all products.
     * @return list products.
     */
    List<Product> getAll();

    /**
     * Get product by id.
     * @param productId product id.
     * @return product.
     */
    Optional<Product> getById(Integer productId); // probably don't need

    /**
     * Update product.
     * @param product product.
     * @return number of updated records.
     */
    Integer update(Product product);

    /**
     * Delete product.
     * @param productId product id.
     * @return number of deleted records.
     */
    Integer delete(Integer productId);
}
