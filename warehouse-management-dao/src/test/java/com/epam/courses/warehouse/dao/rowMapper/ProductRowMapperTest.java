package com.epam.courses.warehouse.dao.rowMapper;

import com.epam.courses.warehouse.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRowMapperTest {

    private Product TEST_PRODUCT = new Product()
            .setProductId(1)
            .setProductName("Product 1");

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ProductRowMapper productRowMapper;

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(resultSet);
    }

    @Test
    public void shouldMapRow() throws SQLException {
        when(resultSet.getInt("PRODUCT_ID")).thenReturn(TEST_PRODUCT.getProductId());
        when(resultSet.getString("PRODUCT_NAME")).thenReturn(TEST_PRODUCT.getProductName());

        Product returnedProduct = productRowMapper.mapRow(resultSet, anyInt());

        assertNotNull(returnedProduct);
        assertNotNull(returnedProduct.getProductName());
        assertNotNull(returnedProduct.getProductId());
        assertEquals(TEST_PRODUCT.getProductId(), returnedProduct.getProductId());
        assertEquals(TEST_PRODUCT.getProductName(), returnedProduct.getProductName());

        verify(resultSet).getInt("PRODUCT_ID");
        verify(resultSet).getString("PRODUCT_NAME");
    }
}