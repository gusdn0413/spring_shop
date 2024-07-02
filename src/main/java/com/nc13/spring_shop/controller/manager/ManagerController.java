package com.nc13.spring_shop.controller.manager;

import com.nc13.spring_shop.controller.item.ItemController;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.MemberService;
import com.nc13.spring_shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/manager/")
@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final OrderService orderService;
    private final ItemController itemController;

    @GetMapping("showAll")
    public String showAll(HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        return "manager/showAll";
    }

}
