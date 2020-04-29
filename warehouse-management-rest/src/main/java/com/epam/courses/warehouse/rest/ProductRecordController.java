package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.rest.exception.ErrorResponse;
import com.epam.courses.warehouse.service.ProductRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ProductRecordController {
    public static final String PRODUCT_NOT_ENOUGH = "product.not_enough";

    Logger LOGGER = LoggerFactory.getLogger(ProductRecordController.class);

    private ProductRecordService productRecordService;

    public ProductRecordController(ProductRecordService service){
        this.productRecordService = service;
    }

    @PostMapping(value = "/records",  consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody ProductRecord productRecord){
        LOGGER.debug("ProductRecordController:create");

        if(productRecord.getDealType().equals(DealTypes.DELIVERY)
                && !productRecordService.shouldGiveOutProduct(productRecord)){
            return new ResponseEntity<>(new ErrorResponse(PRODUCT_NOT_ENOUGH,
                    Collections.singletonList("Product no enough for id = " + productRecord.getProductId())),
            HttpStatus.CONFLICT);
        }
        Integer productRecordId = productRecordService.create(productRecord);
        return new ResponseEntity<>(productRecordId, HttpStatus.OK);
    }
}