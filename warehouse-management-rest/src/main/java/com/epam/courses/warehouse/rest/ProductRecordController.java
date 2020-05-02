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

import static com.epam.courses.warehouse.rest.exception.CustomExceptionHandler.PRODUCT_NOT_ENOUGH;

/**
 * ProductRecord Rest controller.
 */
@RestController
public final class ProductRecordController {

    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordController.class);

    /**
     * ProductRecord service.
     */
    private ProductRecordService productRecordService;

    /**
     * Constructor for ProductRecordController.
     * @param service ProductRecord service.
     */
    public ProductRecordController(final ProductRecordService service) {
        this.productRecordService = service;
    }

    /**
     * Create ProductRecord.
     * @param productRecord ProductRecord.
     * @return <code>ResponseEntity</code>
     */
    @PostMapping(value = "/records",  consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody final ProductRecord productRecord) {
        LOGGER.debug("create({})", productRecord);

        if (productRecord.getDealType().equals(DealTypes.DELIVERY)
                && !productRecordService.shouldGiveOutProduct(productRecord)) {
            return new ResponseEntity<>(new ErrorResponse(PRODUCT_NOT_ENOUGH,
                    Collections.singletonList("Product no enough for id = " + productRecord.getProductId())),
            HttpStatus.CONFLICT);
        }
        Integer productRecordId = productRecordService.create(productRecord);
        return new ResponseEntity<>(productRecordId, HttpStatus.OK);
    }
}
