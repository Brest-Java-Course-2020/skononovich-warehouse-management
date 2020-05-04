package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * ProductRecord service for web app.
 */
public class ProductRecordServiceRest {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRecordServiceRest.class);

    /**
     * URL.
     */
    private String url;

    /**
     * RestTemplate.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor for ProductRecordServiceRest.
     * @param url URL.
     * @param restTemplate RestTemplate.
     */
    public ProductRecordServiceRest(final String url, final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Create product record.
     * @param productRecord ProductRecord.
     * @return productRecord id.
     */
    public Integer create(final ProductRecord productRecord) {
        LOGGER.debug("create({})", productRecord);

        ResponseEntity<Integer> response = restTemplate.postForEntity(url, productRecord, Integer.class);
        return response.getBody();
    }
}
