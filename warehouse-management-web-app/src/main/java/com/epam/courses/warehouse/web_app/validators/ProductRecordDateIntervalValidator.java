package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductRecordDateIntervalValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRecordDateInterval.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startInterval", "startInterval.empty");
        ValidationUtils.rejectIfEmpty(errors, "endInterval", "endInterval.empty");
    }
}
