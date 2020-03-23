package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductRecordDTO;

import java.sql.Date;
import java.util.List;

/**
 * ProductRecordDTO DAO interface.
 */
public interface ProductRecordDtoDAO {

    /**
     * Get all product records with product name.
     * @return product records list.
     */
    List<ProductRecordDTO> getAll();

    /**
     * Get all product records with product name in date range.
     * @param from from date.
     * @param by by date.
     * @return product records list.
     */
    List<ProductRecordDTO> getAllInTimeInterval(Date from, Date by);
}
