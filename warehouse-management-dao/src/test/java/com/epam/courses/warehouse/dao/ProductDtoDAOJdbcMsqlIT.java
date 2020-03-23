package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoDAOJdbcMsqlIT {

    @Autowired
    private ProductDtoDAOJdbcMsql productDtoDAO;

    @Test
    void shouldGetAllProductsWithSummaryCount() {

        List<ProductDto> productDtoList = productDtoDAO.getAllProductsWithSummaryCount();

        assertNotNull(productDtoList);


    }
}