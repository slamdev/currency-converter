package com.github.slamdev.currencyconverter.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.money.MonetaryAmountFactory;
import javax.money.convert.ExchangeRateProvider;

import static javax.money.Monetary.getDefaultAmountFactory;
import static javax.money.convert.MonetaryConversions.getExchangeRateProvider;

@Configuration
public class MonetaryProducer {

    @Bean
    public MonetaryAmountFactory<?> produceMonetaryAmountFactory() {
        return getDefaultAmountFactory();
    }

    @Bean
    public ExchangeRateProvider produceExchangeRateProvider() {
        return getExchangeRateProvider("CL");
    }
}
