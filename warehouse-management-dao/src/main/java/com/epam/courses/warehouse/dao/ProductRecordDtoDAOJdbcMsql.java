package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.dao.rowMapper.ProductRecordDtoRowMapper;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ProductRecordDtoDAOJdbcMsql implements ProductRecordDtoDAO {

    private Logger LOGGER = LoggerFactory.getLogger(ProductRecordDtoDAOJdbcMsql.class);

    @Value("${recordDto.getAll}")
    private String sqlGetAll;

    @Value("${recordDto.getAllInTimeInterval}")
    private String sqlGetAllInTimeInterval;

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ProductRecordDtoRowMapper productRecordDtoRowMapper;

    public ProductRecordDtoDAOJdbcMsql(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                       ProductRecordDtoRowMapper productRecordDtoRowMapper){
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.productRecordDtoRowMapper = productRecordDtoRowMapper;
    }

    @Override
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("ProductRecordDAOJdbcMsql:getAll");
        return jdbcTemplate.query(sqlGetAll, productRecordDtoRowMapper);
    }

    @Override
    public List<ProductRecordDTO> getAllInTimeInterval(Date from, Date to) {
        LOGGER.debug("ProductRecordDAOJdbcMsql:getAllInTimeInterval from: " + from.toString() + " to: " + to);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("from", from)
                .addValue("to", to);
        return jdbcTemplate.query(sqlGetAllInTimeInterval, parameterSource, productRecordDtoRowMapper);
    }
}
