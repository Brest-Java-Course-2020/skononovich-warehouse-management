package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Integer productId);

    void updateProduct(Product product);

    void deleteProduct(Integer productId);
}
