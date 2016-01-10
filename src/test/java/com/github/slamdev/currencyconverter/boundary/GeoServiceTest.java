package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.CurrencyConverterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyConverterApplication.class)
public class GeoServiceTest {

    @Autowired
    private GeoService service;

    @Test
    public void testContainsGermany() {
        assertTrue(service.getAvailableCountries().containsKey("DE"));
    }

    @Test
    public void testContainsRussia() {
        assertTrue(service.getAvailableCountries().containsKey("RU"));
    }
}