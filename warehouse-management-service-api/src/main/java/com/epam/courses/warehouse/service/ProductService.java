package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Product service API.
 */
public interface ProductService {
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
    Optional<Product> getById(Integer productId);

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

    /**
     * <code>Product</code> existence check.
     * @param product product.
     * @return Boolean representation product existence check.
     */
    Boolean isExist(Product product);
}
