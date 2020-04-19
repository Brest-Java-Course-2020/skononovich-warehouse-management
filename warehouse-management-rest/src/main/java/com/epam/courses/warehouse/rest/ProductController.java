package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.rest.exception.ErrorResponse;
import com.epam.courses.warehouse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@EnableSwagger2
@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    public static final String PRODUCT_NOT_FOUND = "product.not_found";
    public static final String PRODUCT_IS_EXIST = "product.is_exist";

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public Collection<Product> getAll(){
        LOGGER.debug("ProductController:getAll");

        return productService.getAll();
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        LOGGER.debug("ProductController:getById id = " + id);

        Optional<Product> optionalProduct = productService.getById(id);
        return optionalProduct.isPresent()
                ? new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK)
                : new ResponseEntity(
                        new ErrorResponse(PRODUCT_NOT_FOUND,
                                Collections.singletonList("Product not found for id:" + id)),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody Product product){
        LOGGER.debug("ProductController:create");

        if(productService.isExist(product)){
            return new ResponseEntity(
                    new ErrorResponse(PRODUCT_IS_EXIST,
                            Collections.singletonList("Product with name " + product.getProductName() + " is exist")),
                    HttpStatus.CONFLICT);
        } else {
            Integer productId = productService.create(product);
            return new ResponseEntity<>(productId, HttpStatus.OK);
        }
    }

    @PutMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> update(@RequestBody Product product){
        LOGGER.debug("ProductController:update");

        Integer numberOfUpdatedRecords = productService.update(product);
        return new ResponseEntity<>(numberOfUpdatedRecords, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        LOGGER.debug("ProductController:delete");

        Integer numberOfUpdatedRecords = productService.delete(id);
        return new ResponseEntity<>(numberOfUpdatedRecords, HttpStatus.OK);
    }
}
