package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDAOJdbcMsql;
import com.epam.courses.warehouse.model.ProductRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRecordServiceMockTest {

    @Mock
    private ProductRecordDAOJdbcMsql productRecordDAO;

    @InjectMocks
    private ProductRecordServiceImpl productRecordService;

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(productRecordDAO);
    }

    @Test
    public void shouldCreateProductRecord(){
        Integer recordId = 4;
        ProductRecord productRecord = new ProductRecord();

        when(productRecordDAO.create(productRecord)).thenReturn(recordId);

        Integer returnedRecordId = productRecordService.create(productRecord);

        assertNotNull(returnedRecordId);
        assertEquals(recordId, returnedRecordId);

        verify(productRecordDAO).create(productRecord);
    }
}
