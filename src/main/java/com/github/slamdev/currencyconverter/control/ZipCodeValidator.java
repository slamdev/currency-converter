package com.github.slamdev.currencyconverter.control;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    private static final Pattern PATTERN = Pattern.compile("[A-Za-z]\\d[A-Za-z]\\s?\\d[A-Za-z]\\d");

    @Override
    public void initialize(ZipCode constraintAnnotation) {
        // nothing to do here
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return PATTERN.matcher(value).matches();
    }
}
