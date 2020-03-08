package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.ProductRecord;

import java.sql.Date;
import java.util.List;

public interface ProductRecordDAO {
    /**
     * Create product record.
     * @param productRecord product record.
     * @return product record id.
     */
    Integer create(ProductRecord productRecord);

    /**
     * Get all product records.
     * @return product records list.
     */
    List<ProductRecord> getAll();

    /**
     * Get all product records in date range.
     * @param from from date.
     * @param by by date.
     * @return product records list.
     */
    List<ProductRecord> getAllProductRecords(Date from, Date by);

    /**
     * Get product count in warehouse.
     * @param product product.
     * @return product count.
     */
    Integer countProductInWarehouse(Product product);

    //TODO: удаление записи о работе с товаром ?
    // редактирование записи о работе с товаром ?
}
