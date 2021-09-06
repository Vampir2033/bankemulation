package ru.dorogin.bankemulation.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public String handleSuccessRegistration(){
        return "Успешная регистрация";
    }
}
