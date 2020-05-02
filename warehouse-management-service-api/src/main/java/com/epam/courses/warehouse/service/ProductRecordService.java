package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.model.ProductRecord;

/**
 * ProductRecord service interface.
 */
public interface ProductRecordService {
    /**
     * Create product record.
     * @param productRecord product record.
     * @return product record id.
     */
    Integer create(ProductRecord productRecord);

    /**
     * Check quantity of product in warehouse.
     * @param productRecord product record.
     * @return Boolean representation, can give out a product or not.
     */
    Boolean shouldGiveOutProduct(ProductRecord productRecord);
}
