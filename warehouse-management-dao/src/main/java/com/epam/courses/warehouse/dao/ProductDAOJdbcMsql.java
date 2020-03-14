package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.dao.RowMaper.ProductRowMapper;
import com.epam.courses.warehouse.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProductDAOJdbcMsql implements ProductDAO {

    private Logger LOGGER = LoggerFactory.getLogger(ProductDAOJdbcMsql.class);

    @Value("${product.create}")
    private String sqlCreateProduct;

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ProductRowMapper productRowMapper;

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    public ProductDAOJdbcMsql(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProductRowMapper productRowMapper){
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.productRowMapper = productRowMapper;
    }

    @Override
    public Integer create(Product product) {
        LOGGER.debug("ProductDAOJdbcMsql:create productName = " + product.getProductName());
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("product_name", product.getProductName());
        jdbcTemplate.update(sqlCreateProduct, parameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Optional<Product> getById(Integer productId) {
        return null;
    }

    @Override
    public Integer update(Product product) {
        return null;
    }

    @Override
    public Integer delete(Integer productId) {
        return null;
    }
}
