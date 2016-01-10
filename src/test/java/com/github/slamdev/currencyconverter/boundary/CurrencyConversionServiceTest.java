package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.CurrencyConverterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static javax.money.Monetary.getCurrency;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyConverterApplication.class)
public class CurrencyConversionServiceTest {

    private static final CurrencyUnit DOLLAR = getCurrency("USD");

    private static final CurrencyUnit EURO = getCurrency("EUR");

    private static final CurrencyUnit RUB = getCurrency("RUB");

    @Autowired
    private CurrencyConversionService service;

    @Autowired
    private MonetaryAmountFactory<?> monetaryFactory;

    @Test
    public void testEurUsdConversion() {
        MonetaryAmount euro10 = of(TEN, EURO);
        MonetaryAmount actual = service.convert(euro10, DOLLAR);
        MonetaryAmount dollar10 = of(TEN, DOLLAR);
        assertTrue(actual.isGreaterThan(dollar10));
    }

    @Test
    public void testRubEurConversion() {
        MonetaryAmount rub10 = of(TEN, RUB);
        MonetaryAmount actual = service.convert(rub10, EURO);
        MonetaryAmount euro10 = of(TEN, EURO);
        assertTrue(actual.isLessThan(euro10));
    }

    private MonetaryAmount of(BigDecimal value, CurrencyUnit unit) {
        return monetaryFactory.setNumber(value).setCurrency(unit).create();
    }
}
