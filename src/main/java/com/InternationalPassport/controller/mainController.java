package com.InternationalPassport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;

@Controller
public class mainController {

    @RequestMapping(value = {"/", "/main"} , method = RequestMethod.GET)
    public String mainPage(Model model) {
        System.out.println("MAINNNNNNNn");
        model.addAttribute("message", "Spring 5");
        return "main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String signIn(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(Model model) {
        return "logIn";
    }
}
