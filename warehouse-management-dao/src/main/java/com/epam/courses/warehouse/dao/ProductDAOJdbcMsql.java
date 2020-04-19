package com.epam.courses.warehouse.dao;

import com.epam.courses.warehouse.dao.rowMapper.ProductRowMapper;
import com.epam.courses.warehouse.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductDAOJdbcMsql implements ProductDAO {

    private Logger LOGGER = LoggerFactory.getLogger(ProductDAOJdbcMsql.class);

    @Value("${product.create}")
    private String sqlCreateProduct;

    @Value("${product.getAll}")
    private String sqlGetAllProducts;

    @Value("${product.getById}")
    private String sqlGetProductById;

    @Value("${product.update}")
    private String sqlUpdateProduct;

    @Value("${product.delete}")
    private String sqlDeleteProduct;

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
        LOGGER.debug("ProductDAOJdbcMsql:getAll");

        return jdbcTemplate.query(sqlGetAllProducts, productRowMapper);
    }

    @Override
    public Optional<Product> getById(Integer productId) {
        LOGGER.debug("ProductDAOJdbcMsql:getById = " + productId);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("product_id", productId);

        List<Product> products = jdbcTemplate.query(sqlGetProductById, parameterSource, productRowMapper);

        return Optional.ofNullable(DataAccessUtils.uniqueResult(products));
    }

    @Override
    public Integer update(Product product) {
        LOGGER.debug("ProductDAOMsql:Update productId = " + product.getProductId());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", product.getProductId())
                .addValue("product_name", product.getProductName());

        return jdbcTemplate.update(sqlUpdateProduct, parameterSource);
    }

    @Override
    public Integer delete(Integer productId) {
        LOGGER.debug("ProductDAOMsql:Delete productId = " + productId);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", productId);
        return jdbcTemplate.update(sqlDeleteProduct, parameterSource);
    }

    @Override
    public Boolean isExist(Product product) {
        LOGGER.debug("ProductDAOJdbcMsql:isExist {}", product);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_name", product.getProductName());

        String sql = "SELECT EXISTS (SELECT * FROM product p WHERE p.product_name = :product_name)";
        Integer countProducts = jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);

        return Objects.requireNonNull(countProducts).equals(1);
    }
}
