package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Objects;


public class ProductRecordDAOJdbcMsql implements ProductRecordDAO{

    private Logger LOGGER = LoggerFactory.getLogger(ProductRecordDtoDAOJdbcMsql.class);

    @Value("${record.create}")
    private String sqlCreateRecord;

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRecordDAOJdbcMsql(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

        @Override
    public Integer create(ProductRecord productRecord) {
        LOGGER.debug("ProductRecordDAOJdbcMsql:create productId = " + productRecord.getProductId());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", productRecord.getProductId())
                .addValue("count", productRecord.getQuantityOfProduct())
                .addValue("date", productRecord.getProductRecordDate())
                .addValue("deal_type", productRecord.getDealType().getValue());

        jdbcTemplate.update(sqlCreateRecord, parameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
