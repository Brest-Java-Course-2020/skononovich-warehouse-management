package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDtoDao;
import com.epam.courses.warehouse.model.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDtoServiceImpl implements ProductDtoService {
    private Logger LOGGER = LoggerFactory.getLogger(ProductDtoServiceImpl.class);

    @Autowired
    private ProductDtoDao productDtoDao;

    @Override
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOGGER.debug("ProductDtoServiceImpl:getAllProductsWithSummaryCount");
        return productDtoDao.getAllProductsWithSummaryCount();
    }
}
