package com.nc13.spring_shop.controller.shop;

import com.nc13.spring_shop.model.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/shop/")
@Controller
public class ShopController {

    @GetMapping("showAll")
    public String showAll(HttpSession session) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        return "shop/showAll";
    }
}
