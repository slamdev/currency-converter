package com.github.slamdev.currencyconverter.boundary;

import com.github.slamdev.currencyconverter.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class AuthController {

    private static final String LOGIN = "login";

    private static final String REGISTER = "register";

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView(LOGIN);
    }

    @RequestMapping("register")
    public ModelAndView register(@ModelAttribute User user) {
        return new ModelAndView(REGISTER);
    }

    @RequestMapping(method = POST)
    public ModelAndView create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(REGISTER, "formErrors", result.getAllErrors());
        }
//        message = this.messageRepository.save(message);
        return new ModelAndView("redirect:/");
    }
}
