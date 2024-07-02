package com.nc13.spring_shop.controller.manager.order;

import com.nc13.spring_shop.model.OrderDTO;
import com.nc13.spring_shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/manager/order/")
@RequiredArgsConstructor
@Controller
public class ManagerOrderController {

    private final OrderService orderService;

    @GetMapping("showAll")
    public String showAll(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

        List<OrderDTO> orderList = orderService.selectAll();
        model.addAttribute("orderList", orderList);
        return "manager/order/showAll";
    }

    @GetMapping("delete/{orderId}")
    public String delete(@PathVariable int orderId, HttpSession session) {

        orderService.delete(orderId);
        return "redirect:/manager/order/showAll";
    }
}
