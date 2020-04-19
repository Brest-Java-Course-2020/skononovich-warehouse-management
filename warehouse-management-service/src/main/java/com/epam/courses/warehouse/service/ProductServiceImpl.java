package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDAO;
import com.epam.courses.warehouse.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public Integer create(Product product) {
        LOGGER.debug("ProductServiceImpl:create");
        return productDAO.create(product);
    }

    @Override
    public List<Product> getAll() {
        LOGGER.debug("ProductServiceImpl:getAll");
        return productDAO.getAll();
    }

    @Override
    public Optional<Product> getById(Integer productId) {
        LOGGER.debug("ProductServiceImpl:getById");
        return productDAO.getById(productId);
    }

    @Override
    public Integer update(Product product) {
        LOGGER.debug("ProductServiceImpl:update");
        return productDAO.update(product);
    }

    @Override
    public Integer delete(Integer productId) {
        LOGGER.debug("ProductServiceImpl:delete");
        return productDAO.delete(productId);
    }

    @Override
    public Boolean isExist(Product product) {
        LOGGER.debug("ProductServiceImpl:isExist {}", product);
        return  productDAO.isExist(product);
    }
}
