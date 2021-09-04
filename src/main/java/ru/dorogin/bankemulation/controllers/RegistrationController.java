package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.services.UserService;

import javax.validation.Valid;


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
    public String addRoute(@ModelAttribute("userForm") @Valid User user,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "registration";
        }
        if(userService.findUserByUsername(user.getUsername()) != null) {
            user.setUsername("Этот email уже используется");
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/login";

    }
}