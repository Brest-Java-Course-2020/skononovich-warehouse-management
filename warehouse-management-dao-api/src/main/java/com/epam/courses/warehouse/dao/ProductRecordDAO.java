package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.ProductRecord;

/**
 * ProductRecord DAO interface.
 */
public interface ProductRecordDAO {
        /**
     * Create product record.
     * @param productRecord product record.
     * @return product record id.
     */
    Integer create(ProductRecord productRecord);
}
