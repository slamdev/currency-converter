package com.github.slamdev.currencyconverter.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;

@Service
public class CurrencyConversionService {

    @Autowired
    private ExchangeRateProvider provider;

    public MonetaryAmount convert(MonetaryAmount from, CurrencyUnit to) {
        CurrencyConversion conversion = provider.getCurrencyConversion(to);
        return from.with(conversion);
    }
}
