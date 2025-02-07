package com.nc13.spring_shop.controller.order.seller;

import com.nc13.spring_shop.model.MemberDTO;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/seller/")
public class OrderSellerController {

    private final OrderService orderService;

    @GetMapping("/showAll/{memberId}")
    public String showAllBySeller(@PathVariable int memberId, Model model,
                                  HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        List<OrderDTO> orderList = orderService.selectAllBySeller(memberId);
        model.addAttribute("orderList", orderList);
        return "order/showAll";
    }
}
