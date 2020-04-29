package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.model.dto.ProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ProductDto service for web app.
 */
public class ProductDtoServiceRest {
    private static final Logger LOOGER = LoggerFactory.getLogger(ProductDtoServiceRest.class);

    private static final TypeReference<List<ProductDto>> TYPE_REFERENCE = new TypeReference<List<ProductDto>>(){};

    private String url;

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductDtoServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Get all products with product quantity.
     * @return <code>ProductDto</code> list.
     */
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOOGER.debug("ProductDtoServiceRest:getAllProductsWithSummaryCount");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return objectMapper.convertValue(responseEntity.getBody(), TYPE_REFERENCE);
    }

    /**
     * Check enough products in warehouse or not.
     * @param productRecord ProductRecord with product quantity.
     * @return boolean.
     */
    public boolean enoughProducts(ProductRecord productRecord){
        List<ProductDto> productDtoList = getAllProductsWithSummaryCount();

        for (ProductDto product : productDtoList){
            if(product.getProductId().equals(productRecord.getProductId())
                    && product.getProductSumCount() >= productRecord.getQuantityOfProduct())
                return true;
        }
        return false;
    }
}