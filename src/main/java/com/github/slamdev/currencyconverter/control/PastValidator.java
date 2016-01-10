package com.github.slamdev.currencyconverter.control;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.UnexpectedTypeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

@Component
public class PastValidator implements ConstraintValidator<Past, Temporal> {

    private int bound;

    private ChronoField field;

    @Override
    public void initialize(Past constraintAnnotation) {
        bound = constraintAnnotation.value();
        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Temporal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof LocalDateTime) {
            return isValid((LocalDateTime) value);
        }
        if (value instanceof LocalDate) {
            return isValid((LocalDate) value);
        }
        throw new UnexpectedTypeException("Valid types are " + LocalDateTime.class + " and " + LocalDate.class);
    }

    private boolean isValid(LocalDateTime value) {
        LocalDateTime date = LocalDateTime.now().minus(bound, field.getBaseUnit());
        return date.isAfter(value);
    }

    private boolean isValid(LocalDate value) {
        LocalDate date = LocalDate.now().minus(bound, field.getBaseUnit());
        return date.isAfter(value);
    }
}
