package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static com.epam.courses.warehouse.model.constants.ProductConstants.PRODUCT_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductValidatorTest {

    @InjectMocks
    private ProductValidator productValidator;

    @Mock
    private ProductServiceRest productServiceRest;

    private BindingResult bindingResult;

    @Mock
    private Product product;

    @BeforeEach
    public void before() {
        bindingResult = new BeanPropertyBindingResult(product, "product");
    }

    @Test
    void shouldProductValidatorSupportsProductClass() {
        assertTrue(productValidator.supports(Product.class));
    }

    @Test
    void shouldRejectIfProductNameIsEmpty()  {
        when(product.getProductName()).thenReturn("");

        productValidator.validate(product, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    void shouldRejectVeryLongProductName() {
        StringBuilder longString = new StringBuilder();
        while (longString.length() < PRODUCT_NAME_SIZE + 1) {
            longString.append("q");
        }

        when(product.getProductName()).thenReturn(longString.toString());

        productValidator.validate(product, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    void shouldRejectIfProductExist(){
        when(productServiceRest.productExist(any(Product.class))).thenReturn(true);

        productValidator.validate(product, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }


}