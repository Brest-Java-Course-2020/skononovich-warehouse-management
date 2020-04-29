package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Product service for web app.
 */
public class ProductServiceRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceRest.class);

    private static final TypeReference<List<Product>> TYPE_REFERENCE = new TypeReference<List<Product>>(){};

    private String url;

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Create Product.
     * @param product Product.
     * @return Product id.
     */
    public Integer create(Product product) {
        LOGGER.debug("ProductServiceRest:create({})", product);

        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, product, Integer.class);
        return responseEntity.getBody();
    }

    /**
     * Get all products.
     * @return <code>Product</code> list.
     */
    public List<Product> getAll() {
        LOGGER.debug("ProductServiceRest:getAll()");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return objectMapper.convertValue(responseEntity.getBody(), TYPE_REFERENCE);
    }

    /**
     * Get <code>Product</code> by id.
     * @param productId product id.
     * @return <code>Product</code>.
     */
    public Optional<Product> getById(Integer productId) {
        LOGGER.debug("ProductServiceRest:findById({})", productId);

        ResponseEntity<Product> responseEntity =
                restTemplate.getForEntity(url + "/" + productId, Product.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    /**
     * Update product.
     * @param product Product.
     * @return Number of updated records.
     */
    public Integer update(Product product) {
        LOGGER.debug("ProductServiceRest:update({})", product);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    /**
     * Delete Product.
     * @param productId product id.
     * @return number of updated records.
     */
    public Integer delete(Integer productId) {
        LOGGER.debug("ProductServiceRest:delete({})", productId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + productId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    /**
     * Check exist product or not.
     * @param prod Product.
     * @return true if product exist.
     */
    public boolean productExist(Product prod){
        List<Product> products = getAll();

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(prod.getProductName()))
                return true;
        }
        return false;
    }
}
