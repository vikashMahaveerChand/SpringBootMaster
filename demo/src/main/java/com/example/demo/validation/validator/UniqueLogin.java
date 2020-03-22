package com.example.demo.validation.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/*@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)*/
public @interface UniqueLogin {
    String message() default "{com.dolszewski.blog.UniqueLogin.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
