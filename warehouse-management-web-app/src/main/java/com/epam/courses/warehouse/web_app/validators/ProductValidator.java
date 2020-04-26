package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.courses.warehouse.model.constants.ProductConstants.PRODUCT_NAME_SIZE;

@Component
public class ProductValidator implements Validator {

    @Autowired
    ProductServiceRest productServiceRest;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productName", "productName.empty");
        Product product = (Product) target;

        if (StringUtils.hasLength(product.getProductName())
                && product.getProductName().length() > PRODUCT_NAME_SIZE) {
            errors.rejectValue("productName", "productName.maxSize");
        }
        if (productServiceRest.productExist(product)) {
            errors.rejectValue("productName", "productName.isExist");
        }
    }
}
