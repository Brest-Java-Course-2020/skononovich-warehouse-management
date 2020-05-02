package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

/**
 * ProductRecordDto service for web app.
 */
public final class ProductRecordDtoServiceRest {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordDtoServiceRest.class);

    /**
     * TypeReference for convert value from String to ProductRecordDTO.
     */
    private static final TypeReference<List<ProductRecordDTO>> TYPE_REFERENCE
            = new TypeReference<List<ProductRecordDTO>>() { };

    /**
     * URL.
     */
    private String url;

    /**
     * Rest template.
     */
    private RestTemplate restTemplate;

    /**
     * Object mapper.
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor for ProductRecordDtoServiceRest.
     * @param url URL.
     * @param restTemplate RestTemplate.
     */
    public ProductRecordDtoServiceRest(final String url,
                                       final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Get all ProductRecords with product name.
     * @return <code>ProductRecordDTO</code> list.
     */
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("getAll()");

        ResponseEntity responseEntity
                = restTemplate.getForEntity(url, List.class);
        return objectMapper.convertValue(responseEntity.getBody(),
                TYPE_REFERENCE);
    }

    /**
     * Get all ProductRecords with product name in time interval.
     * @param from from <code>Date</code>.
     * @param by by <code>Date</code>
     * @return <code>ProductRecordDTO</code> list.
     */
    public List<ProductRecordDTO> getAllInTimeInterval(
            final Date from, final Date by) {
        LOGGER.debug("getAllInTimeInterval({},{})", from, by);

        Date[] dates = new Date[2];
        dates[0] = from;
        dates[1] = by;

        ResponseEntity responseEntity
                = restTemplate.postForEntity(url, dates, List.class);
        return objectMapper
                .convertValue(responseEntity.getBody(), TYPE_REFERENCE);
    }

    /**
     * Check exist product records for <code>Product</code> or not.
     * @param product Product.
     * @return boolean.
     */
    public boolean productRecordExist(final Product product) {
        LOGGER.debug("productRecordExist({})", product);
        List<ProductRecordDTO> productRecordDTOS = getAll();

        for (ProductRecordDTO productRecordDTO : productRecordDTOS) {
            if (productRecordDTO.getProductName()
                    .equalsIgnoreCase(product.getProductName())) {
                return true;
            }
        }
        return false;
    }
}
