package com.Phong.identityservice.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<DobValid, LocalDate> {
    private int min;

    @Override
    public void initialize(DobValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate)) {
            return true;
        }

        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());
        if (years < min) {
            return false;
        }
        return true;
    }
}
