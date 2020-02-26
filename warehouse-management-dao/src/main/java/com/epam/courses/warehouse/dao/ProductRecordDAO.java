package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.ProductRecord;

import java.util.List;

public interface ProductRecordDAO {
    void createProductRecord(ProductRecord productRecord);

    ProductRecord getProductRecordById(Integer productRecordId);

    List<ProductRecord> getAllProductRecords();

    void updateProductRecord(ProductRecord productRecord);

    void deleteProductRecord(ProductRecord productRecord);
}
