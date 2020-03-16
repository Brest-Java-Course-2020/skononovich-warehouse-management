package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml", "classpath*:dao.xml"})
@Transactional
@Rollback
class ProductDAOJdbcMsqlIT {

    private static final Integer FIRST_PRODUCT_ID = 1;
    private static final String FIRST_PRODUCT_NAME = "Product 1";
    private static final String THIRD_PRODUCT_NEW_NAME = "New product name";
    private static final Integer NOT_RELATED_PRODUCT_ID = 3;
    private static final String NOT_RELATED_PRODUCT_NAME = "Product 3";

    @Autowired
    private ProductDAOJdbcMsql productDAOJdbcMsql;

    @Test
    public void shouldCreateProduct(){
        Product product = new Product().setProductName("test");
        Integer productId = productDAOJdbcMsql.create(product);

        assertNotNull(productId);
        }

    @Test
    public void shouldGetAllProducts(){
        List<Product> productList = productDAOJdbcMsql.getAll();

        assertNotNull(productList);
        assertTrue(productList.size() > 0);
    }

    @Test
    public void shouldGetProductById(){
        Optional<Product> optionalProduct = productDAOJdbcMsql.getById(1);

        assertTrue(optionalProduct.isPresent());

        Product productWithId1 = optionalProduct.get();

        assertEquals(FIRST_PRODUCT_ID, productWithId1.getProductId());
        assertEquals(FIRST_PRODUCT_NAME, productWithId1.getProductName());
    }

    @Test
    public void shouldUpdateProduct(){
        Product product = new Product()
                .setProductId(NOT_RELATED_PRODUCT_ID)
                .setProductName(THIRD_PRODUCT_NEW_NAME);

        productDAOJdbcMsql.update(product);

        Optional<Product> optionalUpdatedProduct = productDAOJdbcMsql.getById(NOT_RELATED_PRODUCT_ID);

        assertTrue(optionalUpdatedProduct.isPresent());
        Product updatedProduct = optionalUpdatedProduct.get();

        assertEquals(NOT_RELATED_PRODUCT_ID, updatedProduct.getProductId());
        assertEquals(THIRD_PRODUCT_NEW_NAME, updatedProduct.getProductName());
    }

    @Test
    public void shouldDeleteProduct(){
        int sizeBeforeDelete = productDAOJdbcMsql.getAll().size();

        int numberOfDeletedRecords = productDAOJdbcMsql.delete(NOT_RELATED_PRODUCT_ID);
        int sizeAfterDelete = productDAOJdbcMsql.getAll().size();

        assertEquals(1, numberOfDeletedRecords);
        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

}