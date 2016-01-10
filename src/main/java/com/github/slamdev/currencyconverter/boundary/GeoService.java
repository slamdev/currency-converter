package com.github.slamdev.currencyconverter.boundary;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Locale.getISOCountries;

@Service
public class GeoService {

    private Map<String, String> availableCountries;

    @PostConstruct
    public void init() {
        availableCountries = new HashMap<>();
        stream(getISOCountries()).map(e -> new Locale("", e)).forEach(e -> availableCountries.put(e.getCountry(), e.getDisplayCountry()));
    }

    public Map<String, String> getAvailableCountries() {
        return availableCountries;
    }
}
