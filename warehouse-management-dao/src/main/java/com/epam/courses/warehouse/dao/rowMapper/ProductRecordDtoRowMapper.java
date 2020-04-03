package com.epam.courses.warehouse.dao.rowMapper;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRecordDtoRowMapper implements RowMapper<ProductRecordDTO> {
    @Override
    public ProductRecordDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductRecordDTO productRecordDTO = new ProductRecordDTO()
                .setRecordId(resultSet.getInt("record_id"))
                .setProductName(resultSet.getString("product_name"))
                .setQuantityOfProduct(resultSet.getInt("quantity"))
                .setDealDate(resultSet.getDate("deal_date"))
                .setDealType(DealTypes.fromInt(resultSet.getInt("deal_type")));
        return productRecordDTO;
    }
}
