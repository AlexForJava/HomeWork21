package com.gmail.controller;


import com.gmail.service.User;
import com.gmail.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Space on 24.05.2019.
 */
@Controller
@RequestMapping("/registration")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user)
                .setViewName("registration");
        return modelAndView;
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "login";
    }
}
