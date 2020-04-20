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

    /**
     * Check quantity of product in warehouse.
     * @param productRecord product record.
     * @return <code>Boolean</code> representation, can give out a product or not.
     */
    Boolean shouldGiveOutProduct(ProductRecord productRecord);
}
