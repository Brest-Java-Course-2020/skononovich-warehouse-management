package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDAO;
import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRecordServiceImpl implements ProductRecordService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductRecordServiceImpl.class);

    @Autowired
    private ProductRecordDAO productRecordDAO;

    @Override
    public Integer create(ProductRecord productRecord) {
        LOGGER.debug("ProductRecordDAO:create");

        return productRecordDAO.create(productRecord);
    }

    @Override
    public Boolean shouldGiveOutProduct(ProductRecord productRecord) {
        LOGGER.debug("shouldGiveOutProduct ({})", productRecord);
        return productRecordDAO.shouldGiveOutProduct(productRecord);
    }
}
