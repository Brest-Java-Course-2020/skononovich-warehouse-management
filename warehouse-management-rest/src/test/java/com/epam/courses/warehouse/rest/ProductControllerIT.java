package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Transactional
@Rollback
public class ProductControllerIT {

    public static final String TEST_PRODUCT_NAME = "testProduct";

    @Autowired
    private ProductController productController;

    @Test
    public void shouldGetAllProducts(){

        List<Product> productList = (List<Product>) productController.getAll();

        assertNotNull(productList);
        assertTrue(productList.size() > 0);
    }

    @Test
    public void shouldCreateAndGetProductById(){
        Integer productId = shouldCreateProduct(TEST_PRODUCT_NAME);
        Product product = shouldGetProductById(productId);

        assertEquals(TEST_PRODUCT_NAME, product.getProductName());
    }

    private Integer shouldCreateProduct(String productName){
        ResponseEntity<Integer> response = productController.create(productName);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        return response.getBody();
    }

    private Product shouldGetProductById(Integer productId){
        ResponseEntity<Product> responseProduct = productController.findById(productId);
        assertNotNull(responseProduct);
        assertEquals(HttpStatus.OK, responseProduct.getStatusCode());
        assertNotNull(responseProduct.getBody());

        return responseProduct.getBody();
    }

    @Test
    public void shouldUpdateProduct(){
        Integer productId = shouldCreateProduct(TEST_PRODUCT_NAME);

        Product product = new Product()
                .setProductId(productId)
                .setProductName("newName");

        Integer numberOfUpdatedRecords = productController.update(product).getBody();
        assertEquals((Integer)1, numberOfUpdatedRecords);

        Product updatedProduct = shouldGetProductById(productId);

        assertEquals(product.getProductName(), updatedProduct.getProductName());
    }

    @Test
    public void shouldDeleteProduct(){
        Integer productId = shouldCreateProduct(TEST_PRODUCT_NAME);

        int sizeBeforeDelete = productController.getAll().size();

        Integer numberOfDeletedRecords = productController.delete(productId).getBody();
        assertEquals((Integer)1, numberOfDeletedRecords);

        int sizeAfterDelete = productController.getAll().size();

        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }
}
