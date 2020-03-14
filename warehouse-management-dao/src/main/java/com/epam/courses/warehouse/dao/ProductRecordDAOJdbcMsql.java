package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.ProductRecord;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ProductRecordDAOJdbcMsql implements ProductRecordDAO {

    @Override
    public Integer create(ProductRecord productRecord) {
        return null;
    }

    @Override
    public List<ProductRecord> getAll() {
        return null;
    }

    @Override
    public List<ProductRecord> getAllProductRecords(Date from, Date by) {
        return null;
    }

    @Override
    public Integer countProductInWarehouse(Product product) {
        return null;
    }
}
