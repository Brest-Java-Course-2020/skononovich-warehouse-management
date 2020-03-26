package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.model.dto.ProductDto;

import java.util.List;

/**
 * ProductDto Service interface.
 */
public interface ProductDtoService {

    /**
     * Return list of products with summary count.
     * @return products list.
     */
    List<ProductDto> getAllProductsWithSummaryCount();
}
