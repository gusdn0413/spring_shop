package com.nc13.spring_shop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/showMessage")
    public String showError(@ModelAttribute("message") String message, Model model) {
        System.out.println("message = " + message);

        model.addAttribute("message", message);
        return "showMessage";
    }
}
