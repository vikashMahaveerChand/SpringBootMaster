package com.example.demo.validation.constrait;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.validation.validator.ValidCustomerLocation;

public class CustomerLocationValidator implements ConstraintValidator<ValidCustomerLocation, EmployeeEntity> {
    @Autowired
    Validator validator;

    @Override
    public boolean isValid(EmployeeEntity customerLocation, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        Set<ConstraintViolation<EmployeeEntity>> constraintViolations = new HashSet();
        constraintViolations =
                validator.validate(customerLocation);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            for (ConstraintViolation<EmployeeEntity> violation : constraintViolations) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(violation.getMessageTemplate())
                        .addConstraintViolation();
            }
            isValid = false;
        }
        return isValid;
    }
}
