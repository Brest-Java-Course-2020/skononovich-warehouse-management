package com.epam.courses.warehouse.dao.rowMapper;

import com.epam.courses.warehouse.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("PRODUCT_ID"));
        product.setProductName(resultSet.getString("PRODUCT_NAME"));
        return product;
    }
}
