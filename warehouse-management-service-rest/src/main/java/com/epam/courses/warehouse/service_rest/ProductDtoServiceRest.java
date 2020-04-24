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

public class ProductDtoServiceRest {
    private static final Logger LOOGER = LoggerFactory.getLogger(ProductDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductDtoServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOOGER.debug("ProductDtoServiceRest:getAllProductsWithSummaryCount");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return objectMapper.convertValue(responseEntity.getBody(),
                new TypeReference<List<ProductDto>>(){});
    }

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