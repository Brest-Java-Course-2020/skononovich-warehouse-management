package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDAOJdbcMsql;
import com.epam.courses.warehouse.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplMockTest {

    @Mock
    private ProductDAOJdbcMsql productDAO;

    @InjectMocks
    private ProductServiceImpl productService;

    @AfterEach
    void after(){
        Mockito.verifyNoMoreInteractions(productDAO);
    }

    @Test
    public void shouldCreateProduct(){
        Integer PRODUCT_ID = 5;
        Product product = new Product();

        when(productDAO.create(product)).thenReturn(PRODUCT_ID);

        Integer returnedProductId = productService.create(product);

        assertNotNull(returnedProductId);
        assertEquals(PRODUCT_ID, returnedProductId);

        verify(productDAO).create(product);
    }

    @Test
    public void shouldGetAll(){
        Product product = new Product();

        when(productDAO.getAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.getAll();

        assertNotNull(products);
        assertNotNull(products.get(0));
        assertEquals(product, products.get(0));

        verify(productDAO).getAll();
    }

    @Test
    public void shouldGetProductById(){
        Integer productId = 5;
        Product product = new Product()
                .setProductName("PName")
                .setProductId(productId);

        when(productDAO.getById(productId)).thenReturn(Optional.ofNullable(product));

        Optional<Product> optionalProduct = productService.getById(productId);
        assertTrue(optionalProduct.isPresent());
        Product returnedProduct = optionalProduct.get();

        assertEquals(product, returnedProduct);

        verify(productDAO).getById(productId);
    }

    @Test
    public void shouldUpdateProduct(){

        when(productDAO.update(any(Product.class))).thenReturn(1);

        Integer numberOfUpdatedRecords = productService.update(new Product());

        assertNotNull(numberOfUpdatedRecords);
        assertEquals((Integer)1, numberOfUpdatedRecords);

        verify(productDAO).update(any(Product.class));
    }

    @Test
    public void shouldDeleteProduct(){

        when(productDAO.delete(any(Integer.class))).thenReturn(1);

        Integer numberOfUpdatedRecords = productService.delete(any(Integer.class));

        assertNotNull(numberOfUpdatedRecords);
        assertEquals((Integer)1, numberOfUpdatedRecords);

        verify(productDAO).delete(any(Integer.class));
    }
}
