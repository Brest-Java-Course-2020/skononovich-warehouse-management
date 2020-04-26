package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductRecordServiceRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRecordServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ProductRecordServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public Integer create(ProductRecord productRecord) {
        LOGGER.debug("ProductRecordServiceRest:create");

        ResponseEntity<Integer> response = restTemplate.postForEntity(url, productRecord, Integer.class);
        return response.getBody();
    }
}
