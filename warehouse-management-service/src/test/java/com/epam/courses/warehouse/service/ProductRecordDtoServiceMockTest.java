package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDtoDAOJdbcMsql;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRecordDtoServiceMockTest {

    @Mock
    private ProductRecordDtoDAOJdbcMsql productRecordDtoDao;

    @InjectMocks
    private ProductRecordDtoServiceImpl productRecordDtoService;

    @AfterEach
    void after(){
        verifyNoMoreInteractions(productRecordDtoDao);
    }

    @Test
    public void shouldGetAllRecords(){
        when(productRecordDtoDao.getAll()).thenReturn(Collections.singletonList(new ProductRecordDTO()));

        List<ProductRecordDTO> productRecords = productRecordDtoService.getAll();

        assertNotNull(productRecords);
        assertNotNull(productRecords.get(0));

        verify(productRecordDtoDao).getAll();
    }

    @Test
    public void shouldGetAllRecordsInDateRange(){
        Date from = Date.valueOf("2020-01-12");
        Date to = Date.valueOf("2020-01-13");

        when(productRecordDtoDao.getAllInTimeInterval(from, to))
                .thenReturn(Collections.singletonList(new ProductRecordDTO()));

        List<ProductRecordDTO> productRecords = productRecordDtoService.getAllInTimeInterval(from, to);

        assertNotNull(productRecords);
        assertNotNull(productRecords.get(0));

        verify(productRecordDtoDao).getAllInTimeInterval(from, to);
    }
}
