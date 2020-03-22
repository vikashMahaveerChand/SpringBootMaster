package com.example.demo.validation.constrait;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.validation.validator.ValidCoupon;

public class CouponValidator implements ConstraintValidator<ValidCoupon, String> {
	private String couponPrefix;

	@Override
	public void initialize(ValidCoupon coupon) {
		couponPrefix = coupon.value();
	}

	/*
	 * You can write your own validation logic here. For now I am doing simple
	 * validation here like checking whether coupon code starts with "JAVA" or not.
	 */
	public boolean isValid(String coupon, ConstraintValidatorContext constraintValidatorContext) {
		if (coupon != null) {
			return coupon.startsWith(couponPrefix);
		} else {
			return true;
		}
	}
}
