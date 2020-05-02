package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDtoDAO;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * ProductRecordDto service implementation.
 */
@Service
public class ProductRecordDtoServiceImpl
        implements ProductRecordDtoService {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordDtoServiceImpl.class);

    /**
     * ProductRecordDtoDAO.
     */
    @Autowired
    private ProductRecordDtoDAO productRecordDtoDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("getAll()");
        return productRecordDtoDAO.getAll();
    }

    @Override
    public List<ProductRecordDTO> getAllInTimeInterval(final Date from,
                                                       final Date by) {
        LOGGER.debug("getAllInTimeInterval({},{})", from, by);
        return productRecordDtoDAO.getAllInTimeInterval(from, by);
    }
}
