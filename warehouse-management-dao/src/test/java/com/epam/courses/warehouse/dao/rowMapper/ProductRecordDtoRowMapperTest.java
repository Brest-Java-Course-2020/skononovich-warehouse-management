package com.epam.courses.warehouse.dao.rowMapper;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRecordDtoRowMapperTest {

    ProductRecordDTO TEST_PRODUCT_RECORD_DTO = new ProductRecordDTO()
            .setRecordId(1)
            .setProductName("Product 1")
            .setQuantityOfProduct(1)
            .setDealType(DealTypes.GETTING)
            .setDealDate(Date.valueOf("2020-11-11"));

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ProductRecordDtoRowMapper productRecordDtoRowMapper;

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(resultSet);
    }

    @Test
    void shouldMapRow() throws SQLException {
        when(resultSet.getInt("record_id")).thenReturn(TEST_PRODUCT_RECORD_DTO.getRecordId());
        when(resultSet.getString("product_name")).thenReturn(TEST_PRODUCT_RECORD_DTO.getProductName());
        when(resultSet.getInt("quantity")).thenReturn(TEST_PRODUCT_RECORD_DTO.getQuantityOfProduct());
        when(resultSet.getDate("deal_date")).thenReturn(TEST_PRODUCT_RECORD_DTO.getDealDate());
        when(resultSet.getInt("deal_type")).thenReturn(TEST_PRODUCT_RECORD_DTO.getDealType().getValue());

        ProductRecordDTO returnedProductRecordDTO = productRecordDtoRowMapper.mapRow(resultSet, anyInt());

        assertNotNull(returnedProductRecordDTO);
        assertEquals(TEST_PRODUCT_RECORD_DTO.getRecordId(), returnedProductRecordDTO.getRecordId());
        assertEquals(TEST_PRODUCT_RECORD_DTO.getProductName(), returnedProductRecordDTO.getProductName());
        assertEquals(TEST_PRODUCT_RECORD_DTO.getDealDate(), returnedProductRecordDTO.getDealDate());
        assertEquals(TEST_PRODUCT_RECORD_DTO.getDealType(), returnedProductRecordDTO.getDealType());
        assertEquals(TEST_PRODUCT_RECORD_DTO.getQuantityOfProduct(), returnedProductRecordDTO.getQuantityOfProduct());

        verify(resultSet, times(3)).getInt(anyString());
        verify(resultSet).getString(anyString());
        verify(resultSet).getDate(anyString());
    }
}
