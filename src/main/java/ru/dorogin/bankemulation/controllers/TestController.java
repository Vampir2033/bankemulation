package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

    // todo сделать редирект на регистрацию
    @GetMapping
    @ResponseBody
    String index(){
        return "Hello Test";
    }
}
