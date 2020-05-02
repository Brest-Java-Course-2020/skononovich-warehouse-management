package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MySQL implementation of ProductDtoDAO interface.
 */
@Repository
public class ProductDtoDAOJdbcMsql implements ProductDtoDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductDtoDAOJdbcMsql.class);

    /**
     * SQL script get all products with summary count.
     */
    @Value("${productDto.getAllWithSummaryCount}")
    private String sqlGetAllWithSummaryCount;

    /**
     * Jdbc template.
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Default constructor.
     * @param namedParameterJdbcTemplate jdbc template.
     */
    public ProductDtoDAOJdbcMsql(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * {@inheritDoc}
     * @return ProductDto list.
     */
    @Override
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOGGER.debug("ProductDtoDAOJdbcMsql:getAllProductsWithSummaryCount");

        return jdbcTemplate.query(sqlGetAllWithSummaryCount,
                BeanPropertyRowMapper.newInstance(ProductDto.class));
    }
}
