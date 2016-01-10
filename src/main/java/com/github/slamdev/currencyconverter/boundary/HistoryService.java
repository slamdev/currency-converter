package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.entity.History;
import com.github.slamdev.currencyconverter.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class HistoryService {

    @Value("${currency-converter.historySize}")
    private int maxHistorySize;

    public void log(User user, MonetaryAmount from, CurrencyUnit to) {
        removeObsoleteEntries(user);
        add(user, new History(from, to, now()));
    }

    private void removeObsoleteEntries(User user) {
        List<History> entries = user.getHistoryEntries();
        if (entries.size() <= maxHistorySize) {
            return;
        }
        List<History> obsoleteEntries = entries.subList(maxHistorySize - 1, entries.size());
        obsoleteEntries.forEach(this::remove);
        entries.removeAll(obsoleteEntries);
    }

    private void add(User user, History history) {
        user.getHistoryEntries().add(history);
    }

    private void remove(History history) {
    }
}
