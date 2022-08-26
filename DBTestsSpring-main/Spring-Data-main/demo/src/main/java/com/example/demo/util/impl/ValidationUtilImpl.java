package com.example.demo.util.impl;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtilImpl implements ValidationUtil {
 private final Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        //validator.validate(entity) returns Set of errors if they exist:
        return validator.validate(entity).isEmpty();
    }
}
