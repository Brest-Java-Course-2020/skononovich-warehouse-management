package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml", "classpath*:dao.xml"})
class ProductDAOJdbcMsqlTest {

    @Autowired
    private ProductDAOJdbcMsql productDAOJdbcMsql;

    @Test
    public void shouldCreateProduct(){
        Product product = new Product().setProductName("test");
        Integer productId = productDAOJdbcMsql.create(product);

        assertNotNull(productId);
    }

}