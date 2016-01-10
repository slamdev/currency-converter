package com.github.slamdev.currencyconverter.entity;

import com.github.slamdev.currencyconverter.control.CountryCode;
import com.github.slamdev.currencyconverter.control.Past;
import com.github.slamdev.currencyconverter.control.ZipCode;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private List<History> historyEntries = new ArrayList<>();

    @NotNull
    @Past(100)
    private LocalDate dateOfBirth;

    @NotBlank
    private String street;

    @NotBlank
    @ZipCode
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    @CountryCode
    private String countryCode;

    private String password;
}
