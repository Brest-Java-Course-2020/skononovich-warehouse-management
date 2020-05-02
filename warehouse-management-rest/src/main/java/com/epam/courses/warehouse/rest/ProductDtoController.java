package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service.ProductDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * ProductDto Rest controller.
 */
@RestController
public final class ProductDtoController {
    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductDtoController.class);

    /**
     * ProductDto service.
     */
    private ProductDtoService productDtoService;

    /**
     * Controller.
     * @param service productDto service.
     */
    public ProductDtoController(final ProductDtoService service) {
        this.productDtoService = service;
    }

    /**
     * Get all products with product quantity.
     * @return <code>ProductDto</code> collection.
     */
    @GetMapping(value = "/products_dtos")
    public Collection<ProductDto> products() {
        LOGGER.debug("ProductController:products");

        return productDtoService.getAllProductsWithSummaryCount();
    }
}
