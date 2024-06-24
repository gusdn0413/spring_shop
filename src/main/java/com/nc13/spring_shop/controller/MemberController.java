package com.nc13.spring_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member/")
@Controller
public class MemberController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
