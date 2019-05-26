package com.gmail.controller;

import com.gmail.exceptions.UserNotFoundEception;
import com.gmail.service.User;
import com.gmail.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private final UserService userService;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index", "users", userService.getAll());
    }

    @GetMapping("/editor")
    public ModelAndView editor() {
        return new ModelAndView("editor", "users", userService.getAll());
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user")@NonNull User user) {
        try {
            userService.update(user);
        } catch (UserNotFoundEception e) {
            log.error("There's no user with such id " , e);
        }
        return "redirect:/index";
    }

}
