package com.epam.courses.warehouse.dao.rowMapper;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row mapper for ProductRecordDto.
 */
public final class ProductRecordDtoRowMapper
        implements RowMapper<ProductRecordDTO> {
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductRecordDTO mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        return new ProductRecordDTO()
                .setRecordId(resultSet.getInt("record_id"))
                .setProductName(resultSet.getString("product_name"))
                .setQuantityOfProduct(resultSet.getInt("quantity"))
                .setDealDate(resultSet.getDate("deal_date"))
                .setDealType(DealTypes.fromInt(resultSet.getInt("deal_type")));
    }
}
