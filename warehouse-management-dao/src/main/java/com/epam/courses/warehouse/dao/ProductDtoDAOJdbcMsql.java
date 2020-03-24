package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.model.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDtoDAOJdbcMsql implements ProductDtoDao {
    private Logger LOGGER = LoggerFactory.getLogger(ProductDtoDAOJdbcMsql.class);

    @Value("${productDto.getAllWithSummaryCount}")
    private String sqlGetAllWithSummaryCount;

    NamedParameterJdbcTemplate jdbcTemplate;

    public ProductDtoDAOJdbcMsql(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOGGER.debug("ProductDtoDAOJdbcMsql:getAllProductsWithSummaryCount");

        return jdbcTemplate.query(sqlGetAllWithSummaryCount, BeanPropertyRowMapper.newInstance(ProductDto.class));
    }
}
