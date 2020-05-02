package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import com.epam.courses.warehouse.service.ProductRecordDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * ProductRecordDto Rest controller.
 */
@RestController
public final class ProductRecordDtoController {
    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductRecordDtoController.class);

    /**
     * ProductRecordDto service.
     */
    private ProductRecordDtoService productRecordDtoService;

    /**
     * Constructor for ProductRecordDtoController.
     * @param service productRecordDto service.
     */
    public ProductRecordDtoController(
            final ProductRecordDtoService service) {
        this.productRecordDtoService = service;
    }

    /**
     * Get all ProductRecordDto.
     * @return <code>ProductRecordDTO</code> list.
     */
    @GetMapping(value = "/records_dtos")
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("getAll()");

        return productRecordDtoService.getAll();
    }

    /**
     * Get all ProductRecordDto.
     * @param dates Date interval.
     * @return <code>ProductRecordDTO</code> list.
     */
    @PostMapping(value = "/records_dtos", consumes = "application/json", produces = "application/json")
    public List<ProductRecordDTO> getAllInTimeInterval(@RequestBody final Date[] dates) {
        LOGGER.debug("getAllInTimeInterval({},{})", dates[0], dates[1]);

        return productRecordDtoService.getAllInTimeInterval(dates[0], dates[1]);
    }
}
