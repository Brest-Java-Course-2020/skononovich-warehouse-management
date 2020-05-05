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

/**
 * MySQL implementation ProductRecordDtoDAO interface.
 */
@Repository
public class ProductRecordDtoDAOJdbc implements ProductRecordDtoDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordDtoDAOJdbc.class);

    /**
     * SQL script get all records.
     */
    @Value("${recordDto.getAll}")
    private String sqlGetAll;

    /**
     * SQL script get all records in date interval.
     */
    @Value("${recordDto.getAllInTimeInterval}")
    private String sqlGetAllInTimeInterval;

    /**
     * Jdbc template.
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * ProductRecordDto row mapper.
     */
    private ProductRecordDtoRowMapper productRecordDtoRowMapper;

    /**
     * Constructor.
     * @param namedParameterJdbcTemplate jdbc template.
     * @param rowMapper row mapper.
     */
    public ProductRecordDtoDAOJdbc(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            final ProductRecordDtoRowMapper rowMapper) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.productRecordDtoRowMapper = rowMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("getAll()");
        return jdbcTemplate.query(sqlGetAll, productRecordDtoRowMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRecordDTO> getAllInTimeInterval(final Date from,
                                                       final Date to) {
        LOGGER.debug("getAllInTimeInterval({},{})", from, to);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("from", from)
                .addValue("to", to);
        return jdbcTemplate.query(sqlGetAllInTimeInterval,
                parameterSource, productRecordDtoRowMapper);
    }
}
