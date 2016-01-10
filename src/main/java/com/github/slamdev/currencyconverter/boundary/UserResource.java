package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(UserResource.USER)
public class UserResource {

    public static final String USER = "user";
    public static final String ALL = "all";

    @RequestMapping(method = GET, value = ALL)
    public List<User> getAll() {
        return emptyList();
    }

    @RequestMapping(method = GET, value = "{id}")
    public User getUser(long id) {
        return null;
    }
}
