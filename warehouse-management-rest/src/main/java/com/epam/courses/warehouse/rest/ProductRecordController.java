package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.service.ProductRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRecordController {
    Logger LOGGER = LoggerFactory.getLogger(ProductRecordController.class);

    private ProductRecordService productRecordService;

    public ProductRecordController(ProductRecordService service){
        this.productRecordService = service;
    }

    @PostMapping(value = "/records",  consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> create(@RequestBody ProductRecord productRecord){
        LOGGER.debug("ProductRecordController:create");

        return new ResponseEntity<>(productRecordService.create(productRecord), HttpStatus.OK);
    }

}
