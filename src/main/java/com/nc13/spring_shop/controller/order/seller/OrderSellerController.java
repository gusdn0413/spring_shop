package com.nc13.spring_shop.controller.order.seller;

import com.nc13.spring_shop.model.OrderDTO;
import com.nc13.spring_shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/seller/")
public class OrderSellerController {

    private final OrderService orderService;

    @GetMapping("/showAll/{memberId}")
    public String showAllBySeller(@PathVariable int memberId, Model model, HttpSession session) {
        List<OrderDTO> orderList = orderService.selectAllBySeller(memberId);
        model.addAttribute("orderList", orderList);
        return "order/showAll";
    }
}
