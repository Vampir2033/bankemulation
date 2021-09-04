package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.services.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping
    public String addRoute(@ModelAttribute("userForm") User user){
        userService.saveUser(user);
        return "redirect:/login";

    }
}