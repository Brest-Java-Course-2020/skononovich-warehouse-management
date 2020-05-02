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

/**
 * MySQL implementation of ProductDAOJdbc interface.
 */
@Repository
public class ProductDAOJdbcMsql implements ProductDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductDAOJdbcMsql.class);

    /**
     * SQL script create product.
     */
    @Value("${product.create}")
    private String sqlCreateProduct;

    /**
     * SQL script get all products.
     */
    @Value("${product.getAll}")
    private String sqlGetAllProducts;

    /**
     * SQL script get product by id.
     */
    @Value("${product.getById}")
    private String sqlGetProductById;

    /**
     * SQL update product.
     */
    @Value("${product.update}")
    private String sqlUpdateProduct;

    /**
     * SQL delete product.
     */
    @Value("${product.delete}")
    private String sqlDeleteProduct;

    /**
     * SQL script check exist product or not.
     */
    @Value("${product.isProductExist}")
    private String sqlIsProductExist;

    /**
     * Jdbc template.
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Product Row mapper.
     */
    private ProductRowMapper productRowMapper;

    /**
     * Key holder.
     */
    private KeyHolder keyHolder = new GeneratedKeyHolder();

    /**
     * Constructor.
     * @param namedParameterJdbcTemplate jdbc template.
     * @param rowMapper row mapper.
     */
    public ProductDAOJdbcMsql(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            final ProductRowMapper rowMapper) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.productRowMapper = rowMapper;
    }

    /**
     * {@inheritDoc}
     * @param product product.
     * @return product id.
     */
    @Override
    public final Integer create(final Product product) {
        LOGGER.debug("create({})", product);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("product_name", product.getProductName());

        jdbcTemplate.update(sqlCreateProduct, parameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * {@inheritDoc}
     * @return Product list.
     */
    @Override
    public final List<Product> getAll() {
        LOGGER.debug("ProductDAOJdbcMsql:getAll");

        return jdbcTemplate.query(sqlGetAllProducts, productRowMapper);
    }

    /**
     * {@inheritDoc}
     * @param productId product id.
     * @return Optional product.
     */
    @Override
    public final Optional<Product> getById(final Integer productId) {
        LOGGER.debug("getById({})", productId);

        MapSqlParameterSource parameterSource
                = new MapSqlParameterSource()
                .addValue("product_id", productId);

        List<Product> products
                = jdbcTemplate.query(
                        sqlGetProductById, parameterSource, productRowMapper);

        return Optional.ofNullable(DataAccessUtils.uniqueResult(products));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Integer update(final Product product) {
        LOGGER.debug("update({})", product);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", product.getProductId())
                .addValue("product_name", product.getProductName());

        return jdbcTemplate.update(sqlUpdateProduct, parameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Integer delete(final Integer productId) {
        LOGGER.debug("delete({})", productId);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", productId);
        return jdbcTemplate.update(sqlDeleteProduct, parameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean isExist(final Product product) {
        LOGGER.debug("isExist({})", product);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_name", product.getProductName());

        Integer countProducts = jdbcTemplate.queryForObject(
                sqlIsProductExist, parameterSource, Integer.class);

        return Objects.requireNonNull(countProducts).equals(1);
    }
}
