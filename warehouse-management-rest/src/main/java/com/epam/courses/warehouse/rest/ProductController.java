package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.rest.exception.ErrorResponse;
import com.epam.courses.warehouse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.epam.courses.warehouse.rest.exception.CustomExceptionHandler.PRODUCT_IS_EXIST;
import static com.epam.courses.warehouse.rest.exception.CustomExceptionHandler.PRODUCT_NOT_FOUND;

/**
 * Product Rest controller.
 */
@RestController
public final class ProductController {
    /**
     * Logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductController.class);

    /**
     * ProductService.
     */
    private final ProductService productService;

    /**
     * Constructor for ProductController.
     * @param service product setvice.
     */
    public ProductController(final ProductService service) {
        this.productService = service;
    }

    /**
     * Get all products.
     * @return <code>Product</code> collection.
     */
    @GetMapping(value = "/products")
    public Collection<Product> getAll() {
        LOGGER.debug("getAll()");

        return productService.getAll();
    }

    /**
     * Get product by id.
     * @param id product id.
     * @return <code>ResponseEntity</code>
     */
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable final Integer id) {
        LOGGER.debug("findById({})", id);

        Optional<Product> optionalProduct = productService.getById(id);
        return optionalProduct.map(
                product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(
                new ErrorResponse(PRODUCT_NOT_FOUND,
                        Collections.singletonList("Product not found for id:" + id)),
                HttpStatus.NOT_FOUND));
    }

    /**
     * Create product.
     * @param product Product.
     * @return <code>ResponseEntity</code>
     */
    @PostMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody final Product product) {
        LOGGER.debug("create({})", product);

        if (productService.isExist(product)) {
            return new ResponseEntity(
                    new ErrorResponse(PRODUCT_IS_EXIST,
                            Collections.singletonList("Product with name " + product.getProductName() + " is exist")),
                    HttpStatus.CONFLICT);
        } else {
            Integer productId = productService.create(product);
            return new ResponseEntity<>(productId, HttpStatus.OK);
        }
    }

    /**
     * Update product.
     * @param product Product.
     * @return <code>ResponseEntity</code>
     */
    @PutMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> update(@RequestBody final Product product) {
        LOGGER.debug("update({})", product);

        Integer numberOfUpdatedRecords = productService.update(product);
        return new ResponseEntity<>(numberOfUpdatedRecords, HttpStatus.OK);
    }

    /**
     * Delete product.
     * @param id product id.
     * @return <code>ResponseEntity</code>
     */
    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Integer> delete(@PathVariable final Integer id) {
        LOGGER.debug("delete({})", id);

        Integer numberOfUpdatedRecords = productService.delete(id);
        return new ResponseEntity<>(numberOfUpdatedRecords, HttpStatus.OK);
    }
}
