package com.github.slamdev.currencyconverter.control;

import com.github.slamdev.currencyconverter.boundary.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {

    @Autowired
    private GeoService geoService;

    @Override
    public void initialize(CountryCode constraintAnnotation) {
        // nothing to do here
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return geoService.getAvailableCountries().containsKey(value);
    }
}
