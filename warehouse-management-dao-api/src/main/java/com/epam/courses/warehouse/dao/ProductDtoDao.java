package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductDto;

import java.util.List;

/**
 * ProductDto DAO interface.
 */
public interface ProductDtoDao {

    /**
     * Return list of products with summary count.
     * @return products list.
     */
    List<ProductDto> getAllProductsWithSummaryCount();
}
