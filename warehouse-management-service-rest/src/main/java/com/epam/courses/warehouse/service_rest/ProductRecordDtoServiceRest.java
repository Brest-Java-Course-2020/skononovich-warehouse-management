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

public class ProductRecordDtoServiceRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRecordDtoServiceRest.class);

    private static final TypeReference<List<ProductRecordDTO>> TYPE_REFERENCE =
            new TypeReference<List<ProductRecordDTO>>() {};

    private String url;

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductRecordDtoServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("ProductRecordDtoServiceRest:getAll");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return objectMapper.convertValue(responseEntity.getBody(), TYPE_REFERENCE);
    }

    public List<ProductRecordDTO> getAllInTimeInterval(Date from, Date by) {
        LOGGER.debug("ProductRecordDtoServiceRest:getAll");

        Date[] dates = new Date[2];
        dates[0] = from;
        dates[1] = by;

        ResponseEntity responseEntity = restTemplate.postForEntity(url, dates, List.class);
        return objectMapper.convertValue(responseEntity.getBody(), TYPE_REFERENCE);
    }

    public boolean productRecordExist(Product product){
        List<ProductRecordDTO> productRecordDTOS = getAll();

        for (ProductRecordDTO productRecordDTO : productRecordDTOS) {
            if (productRecordDTO.getProductName().equalsIgnoreCase(product.getProductName())) {
                return true;
            }
        }
        return false;
    }
}
