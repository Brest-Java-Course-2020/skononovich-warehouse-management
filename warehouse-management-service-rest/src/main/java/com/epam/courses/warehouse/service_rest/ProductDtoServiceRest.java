package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service.ProductDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProductDtoServiceRest implements ProductDtoService {
    private static final Logger LOOGER = LoggerFactory.getLogger(ProductDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ProductDtoServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> getAllProductsWithSummaryCount() {
        LOOGER.debug("ProductDtoServiceRest:getAllProductsWithSummaryCount");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<ProductDto>) responseEntity.getBody();
    }
}