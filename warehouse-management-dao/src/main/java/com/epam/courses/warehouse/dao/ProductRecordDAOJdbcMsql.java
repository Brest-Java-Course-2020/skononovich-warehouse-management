package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * MySQL implementation of ProductRecordDAO.
 */
@Repository
public class ProductRecordDAOJdbcMsql implements ProductRecordDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordDtoDAOJdbcMsql.class);

    /**
     * SQL script create record.
     */
    @Value("${record.create}")
    private String sqlCreateRecord;

    /**
     * SQL script should give out product.
     */
    @Value("${record.shouldGiveOutProduct}")
    private String sqlShouldGiveOutProduct;

    /**
     * Jdbc template.
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Constructor.
     * @param namedParameterJdbcTemplate jdbc template.
     */
    public ProductRecordDAOJdbcMsql(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(final ProductRecord productRecord) {
        LOGGER.debug("create({})", productRecord.getProductId());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", productRecord.getProductId())
                .addValue("count", productRecord.getQuantityOfProduct())
                .addValue("date", productRecord.getProductRecordDate())
                .addValue("deal_type", productRecord.getDealType().getValue());

        jdbcTemplate.update(sqlCreateRecord, parameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean shouldGiveOutProduct(final ProductRecord productRecord) {
        LOGGER.debug("shouldGiveOutProduct ({})", productRecord);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", productRecord.getProductId());

        Integer response = jdbcTemplate.queryForObject(sqlShouldGiveOutProduct,
                parameterSource, Integer.class);

        return Objects.requireNonNull(response)
                >= productRecord.getQuantityOfProduct();
    }
}
