package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDAO;
import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductRecord service implementation.
 */
@Service
public final class ProductRecordServiceImpl implements ProductRecordService {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordServiceImpl.class);

    /**
     * ProductRecordDao.
     */
    @Autowired
    private ProductRecordDAO productRecordDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(final ProductRecord productRecord) {
        LOGGER.debug("create({})", productRecord);

        return productRecordDAO.create(productRecord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean shouldGiveOutProduct(final ProductRecord productRecord) {
        LOGGER.debug("shouldGiveOutProduct ({})", productRecord);
        return productRecordDAO.shouldGiveOutProduct(productRecord);
    }
}
