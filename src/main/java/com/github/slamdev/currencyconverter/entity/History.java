package com.github.slamdev.currencyconverter.entity;

import com.github.slamdev.currencyconverter.control.Past;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class History implements Comparable<History> {

    @NotNull
    private MonetaryAmount from;

    @NotNull
    private CurrencyUnit to;

    @NotNull
    @Past
    private LocalDateTime date;

    @Override
    public int compareTo(History o) {
        return date.compareTo(o.date);
    }
}
