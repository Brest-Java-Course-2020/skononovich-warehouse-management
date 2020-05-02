package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductDAO;
import com.epam.courses.warehouse.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Product service implementation.
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * Product DAO.
     */
    private ProductDAO productDAO;

    /**
     * Constructor.
     * @param dao product dao.
     */
    public ProductServiceImpl(final ProductDAO dao) {
        this.productDAO = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(final Product product) {
        LOGGER.debug("create({})", product);
        return productDAO.create(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAll() {
        LOGGER.debug("getAll()");
        return productDAO.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Product> getById(final Integer productId) {
        LOGGER.debug("getById({})", productId);
        return productDAO.getById(productId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(final Product product) {
        LOGGER.debug("uppdate({})", product);
        return productDAO.update(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer delete(final Integer productId) {
        LOGGER.debug("delete({})", productId);
        return productDAO.delete(productId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isExist(final Product product) {
        LOGGER.debug("isExist({}", product);
        return  productDAO.isExist(product);
    }
}
