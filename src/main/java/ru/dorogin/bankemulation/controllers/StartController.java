package ru.dorogin.bankemulation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {

    @GetMapping
    String index(){
        return "redirect:/registration";
    }
}