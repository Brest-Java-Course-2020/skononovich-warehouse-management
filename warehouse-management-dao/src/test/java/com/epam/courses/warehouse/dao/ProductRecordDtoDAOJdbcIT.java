package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml", "classpath*:dao.xml"})
@Transactional
@Rollback
public class ProductRecordDtoDAOJdbcIT {
    private static final int PRODUCT_RECORDS_IN_DATABASE = 5;

    private static final Date FROM_DATE = Date.valueOf("2020-01-12");
    private static final Date TO_DATE = Date.valueOf("2020-02-01");
    private static final int RECORDS_IN_TIME_INTERVAL = 3;

    @Autowired
    private ProductRecordDtoDAOJdbc productRecordDtoDAO;

    @Test
    public void shouldGetAllProductRecords(){
        List<ProductRecordDTO> productRecordList = productRecordDtoDAO.getAll();
        assertNotNull(productRecordList);
        assertEquals(PRODUCT_RECORDS_IN_DATABASE, productRecordList.size());
    }

    @Test
    public void shouldGetAllProductRecordsInTimeInterval(){
        List<ProductRecordDTO> productRecordListInTimeInterval = productRecordDtoDAO.getAllInTimeInterval(FROM_DATE, TO_DATE);
        assertNotNull(productRecordListInTimeInterval);
        assertEquals(RECORDS_IN_TIME_INTERVAL, productRecordListInTimeInterval.size());
    }
}
