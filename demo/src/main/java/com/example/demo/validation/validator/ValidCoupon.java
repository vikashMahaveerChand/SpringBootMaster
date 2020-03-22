package com.example.demo.validation.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.validation.constrait.CouponValidator;

@Constraint(validatedBy = CouponValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoupon {
	  public String value() default "JAVA";
	  public String message() default "Coupon Code must starts with JAVA";
	  public Class<?>[] groups() default {};
	  public Class<? extends Payload>[] payload() default{};
}
