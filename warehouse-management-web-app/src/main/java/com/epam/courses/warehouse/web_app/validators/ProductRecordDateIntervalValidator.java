package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for date filter.
 */
@Component
public class ProductRecordDateIntervalValidator implements Validator {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRecordDateInterval.class.equals(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startInterval", "startInterval.empty");
        ValidationUtils.rejectIfEmpty(errors, "endInterval", "endInterval.empty");
    }
}
