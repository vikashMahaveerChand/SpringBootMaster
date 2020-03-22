package com.example.demo.validation.validator;

import java.lang.annotation.Documented;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.validation.constrait.CustomerLocationValidator;

/*@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)*/
@Constraint(validatedBy = {CustomerLocationValidator.class})
@Documented
public @interface ValidCustomerLocation {
    String message() default "Invalid customer location";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
