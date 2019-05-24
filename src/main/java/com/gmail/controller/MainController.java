package com.gmail.controller;

import com.gmail.service.User;
import com.gmail.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private final UserService userService;

    @GetMapping("/index")
    public ModelAndView index() {
        User user = new User()
                .setUserName("alex")
                .setId(1l)
                .setEmail("1232sdsd");
        List<User> userList = Collections.singletonList(user);
        return new ModelAndView("index", "users", userList);
    }

    @GetMapping("/editor")
    public ModelAndView editor() {
        return new ModelAndView("editor", "users", userService.getAll());
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/index";
    }

}
