package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.CurrencyConverterApplication;
import com.github.slamdev.currencyconverter.entity.History;
import com.github.slamdev.currencyconverter.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static javax.money.Monetary.getCurrencies;
import static javax.money.Monetary.getCurrency;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyConverterApplication.class)
public class HistoryServiceTest {

    @Value("${currency-converter.historySize}")
    private int maxHistorySize;

    @Autowired
    private HistoryService service;

    @Autowired
    private MonetaryAmountFactory<?> monetaryFactory;

    private MonetaryAmount eur10;

    private CurrencyUnit rub;

    @Before
    public void before() {
        eur10 = monetaryFactory.setNumber(10).setCurrency(getCurrency("EUR")).create();
        rub = getCurrency("RUB");
    }

    @Test
    public void testEntryAdded() {
        User user = new User();
        service.log(user, eur10, rub);
        assertTrue(user.getHistoryEntries().stream().anyMatch(e -> e.getFrom().equals(eur10) && e.getTo().equals(rub)));
    }

    @Test
    public void testMaxHistorySize() {
        User user = new User();
        fillRandomHistoryEntries(user.getHistoryEntries(), current().nextInt(maxHistorySize, maxHistorySize + 100));
        assertTrue(user.getHistoryEntries().size() > maxHistorySize);
        service.log(user, eur10, rub);
        assertEquals(maxHistorySize, user.getHistoryEntries().size());
        assertTrue(user.getHistoryEntries().stream().anyMatch(e -> e.getFrom().equals(eur10) && e.getTo().equals(rub)));
    }

    private void fillRandomHistoryEntries(List<History> historyEntries, int size) {
        historyEntries.addAll(range(0, size).mapToObj(this::randomHistoryEntry).collect(toList()));
    }

    private History randomHistoryEntry(int seed) {
        List<CurrencyUnit> currencies = new ArrayList<>(getCurrencies());
        int randomIndex1 = current().nextInt(currencies.size());
        int randomIndex2 = current().nextInt(currencies.size());
        MonetaryAmount euro = monetaryFactory.setNumber(seed).setCurrency(currencies.get(randomIndex1)).create();
        return new History(euro, currencies.get(randomIndex2), now());
    }
}