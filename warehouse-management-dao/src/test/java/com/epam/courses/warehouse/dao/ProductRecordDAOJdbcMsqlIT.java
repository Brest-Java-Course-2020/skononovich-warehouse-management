package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml", "classpath*:dao.xml"})
@Transactional
@Rollback
public class ProductRecordDAOJdbcMsqlIT {
    private static final int PRODUCT_ID = 1;
    private static final int QUANTITY_OF_PRODUCT = 4;
    private static final Date RECORD_DATE = Date.valueOf("2020-05-30");
    private static final DealTypes DEAL_TYPE = DealTypes.DELIVERY;

    @Autowired
    private ProductRecordDAOJdbcMsql productRecordDAO;

    //TODO: This is good practice?
    @Autowired
    private ProductRecordDtoDAOJdbcMsql productRecordDtoDAO;

    @Test
    public void shouldCreateProductRecord(){
        ProductRecord productRecord = new ProductRecord()
                .setProductId(PRODUCT_ID)
                .setQuantityOfProduct(QUANTITY_OF_PRODUCT)
                .setProductRecordDate(RECORD_DATE)
                .setDealType(DEAL_TYPE);

        int sizeBeforeCreateNewRecord = productRecordDtoDAO.getAll().size();
        Integer addedProductRecordId = productRecordDAO.create(productRecord);
        int sizeAfterCreateNewRecord = productRecordDtoDAO.getAll().size();

        assertNotNull(addedProductRecordId);
        assertEquals(sizeBeforeCreateNewRecord + 1, sizeAfterCreateNewRecord);
    }
}
