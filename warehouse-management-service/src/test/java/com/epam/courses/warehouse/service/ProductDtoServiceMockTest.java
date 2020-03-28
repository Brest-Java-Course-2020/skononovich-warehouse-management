package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDtoDAOJdbcMsql;
import com.epam.courses.warehouse.model.dto.ProductDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductDtoServiceMockTest {

    @Mock
    private ProductDtoDAOJdbcMsql productDtoDAO;

    @InjectMocks
    private ProductDtoServiceImpl productDtoService;

    @AfterEach
    void after(){
        verifyNoMoreInteractions(productDtoDAO);
    }

    @Test
    public void shouldGetAllWithSummaryCount(){
        ProductDto productDto = new ProductDto();

        when(productDtoDAO.getAllProductsWithSummaryCount()).thenReturn(Collections.singletonList(productDto));

        List<ProductDto> products = productDtoService.getAllProductsWithSummaryCount();

        assertNotNull(products);
        assertNotNull(products.get(0));

        verify(productDtoDAO).getAllProductsWithSummaryCount();
    }
}
