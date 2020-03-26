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
}
