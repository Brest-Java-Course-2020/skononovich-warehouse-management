package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDtoDao;
import com.epam.courses.warehouse.model.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductDto service implementation.
 */
@Service
public final class ProductDtoServiceImpl implements ProductDtoService {
    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductDtoServiceImpl.class);

    /**
     * ProductDtoDao.
     */
    @Autowired
    private ProductDtoDao productDtoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOGGER.debug("getAllProductsWithSummaryCount()");
        return productDtoDao.getAllProductsWithSummaryCount();
    }
}
