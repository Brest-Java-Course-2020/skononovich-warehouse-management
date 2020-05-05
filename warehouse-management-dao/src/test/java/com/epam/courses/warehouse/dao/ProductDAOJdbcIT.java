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
class ProductDAOJdbcIT {

    private static final Integer FIRST_PRODUCT_ID = 1;
    private static final String FIRST_PRODUCT_NAME = "Product 1";
    private static final String THIRD_PRODUCT_NEW_NAME = "New product name";
    private static final Integer NOT_RELATED_PRODUCT_ID = 3;
    private static final String TEST_PRODUCT = "PRODUCT";

    @Autowired
    private ProductDAOJdbc productDAOJdbc;

    @Test
    public void shouldCreateProduct(){
        Product product = new Product().setProductName("test");
        Integer productId = productDAOJdbc.create(product);

        assertNotNull(productId);
        }

    @Test
    public void shouldGetAllProducts(){
        List<Product> productList = productDAOJdbc.getAll();

        assertNotNull(productList);
        assertTrue(productList.size() > 0);
    }

    @Test
    public void shouldGetProductById(){
        Optional<Product> optionalProduct = productDAOJdbc.getById(1);

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

        productDAOJdbc.update(product);

        Optional<Product> optionalUpdatedProduct = productDAOJdbc.getById(NOT_RELATED_PRODUCT_ID);

        assertTrue(optionalUpdatedProduct.isPresent());
        Product updatedProduct = optionalUpdatedProduct.get();

        assertEquals(NOT_RELATED_PRODUCT_ID, updatedProduct.getProductId());
        assertEquals(THIRD_PRODUCT_NEW_NAME, updatedProduct.getProductName());
    }

    @Test
    public void shouldDeleteProduct(){
        int sizeBeforeDelete = productDAOJdbc.getAll().size();

        int numberOfDeletedRecords = productDAOJdbc.delete(NOT_RELATED_PRODUCT_ID);
        int sizeAfterDelete = productDAOJdbc.getAll().size();

        assertEquals(1, numberOfDeletedRecords);
        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    public void shouldCheckProductExistence(){
        Product product = new Product().setProductName(TEST_PRODUCT);
        productDAOJdbc.create(product);

        Boolean productExistence = productDAOJdbc.isExist(product);

        assertNotNull(productExistence);
        assertTrue(productExistence);
    }
}