package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml", "classpath*:dao.xml"})
@Transactional
@Rollback
class ProductDtoDAOJdbcMsqlIT {
    private static final int RECORDS_IN_LIST = 3;

    @Autowired
    private ProductDtoDAOJdbcMsql productDtoDAO;

    @Test
    void shouldGetAllProductsWithSummaryCount() {

        List<ProductDto> productDtoList = productDtoDAO.getAllProductsWithSummaryCount();

        assertNotNull(productDtoList);
        assertEquals(RECORDS_IN_LIST, productDtoDAO.getAllProductsWithSummaryCount().size());
    }
}