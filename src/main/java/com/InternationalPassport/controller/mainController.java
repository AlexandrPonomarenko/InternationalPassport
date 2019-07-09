package com.InternationalPassport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String MainPage(Model model) {
        model.addAttribute("message", "Spring 5");
        return "main";
    }
}