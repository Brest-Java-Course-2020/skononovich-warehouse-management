package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import com.epam.courses.warehouse.service.ProductRecordDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

public class ProductRecordDtoServiceRest implements ProductRecordDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRecordDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ProductRecordDtoServiceRest(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("ProductRecordDtoServiceRest:getAll");

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<ProductRecordDTO>) responseEntity.getBody();
    }

    @Override
    public List<ProductRecordDTO> getAllInTimeInterval(Date from, Date by) {
        LOGGER.debug("ProductRecordDtoServiceRest:getAll");

        Date[] dates = new Date[2];
        dates[0] = from;
        dates[1] = by;

        ResponseEntity responseEntity = restTemplate.postForEntity(url, dates, List.class);
        return (List<ProductRecordDTO>) responseEntity.getBody();
    }
}
