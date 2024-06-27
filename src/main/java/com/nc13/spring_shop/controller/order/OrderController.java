package com.nc13.spring_shop.controller.order;

import com.nc13.spring_shop.model.ItemDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.model.OrderDTO;
import com.nc13.spring_shop.service.ItemService;
import com.nc13.spring_shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/")
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/insert/{itemId}")
    public String showInsert(@PathVariable int itemId, HttpSession session, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        ItemDTO itemDTO = itemService.selectOne(itemId);
        if (itemDTO == null) {
            return "redirect:/item/showAll";
        }
        model.addAttribute("itemDTO", itemDTO);
        return "/order/insert";
    }

    @PostMapping("insert/{itemId}")
    public String insert(@PathVariable int itemId, HttpSession session, OrderDTO orderDTO) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        ItemDTO itemDTO = itemService.selectOne(itemId);
        if (itemDTO == null) {
            return "redirect:/item/showAll";
        }

        orderDTO.setItemId(itemId);
        orderDTO.setPrice(itemDTO.getPrice() * orderDTO.getQuantity());
        orderDTO.setEmail(login.getEmail());
        orderDTO.setMemberCustomerId(login.getId());
        orderDTO.setMemberSellerId(itemDTO.getMemberSellerId());
        orderDTO.setName(itemDTO.getName());
        itemDTO.setQuantity(itemDTO.getQuantity() - orderDTO.getQuantity());

        itemService.updateQuantity(itemDTO);
        orderService.insert(orderDTO);

        return "redirect:/item/showOne/" + orderDTO.getItemId();
    }

    @GetMapping("/showAll/{memberId}")
    public String showAll(@PathVariable int memberId, Model model, HttpSession session) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (memberId != login.getId()) {
            //권한이 없습니다
        }

        List<OrderDTO> orderList = orderService.selectAllByCustomer(memberId);
        model.addAttribute("orderList", orderList);

        return "order/showAll";
    }

    @GetMapping("showOne/{orderId}")
    public String showOne(@PathVariable int orderId, HttpSession session, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        OrderDTO orderDTO = orderService.selectOne(orderId);
        model.addAttribute("orderDTO", orderDTO);
        return "order/showOne";
    }
}
