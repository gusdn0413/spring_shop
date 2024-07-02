package com.nc13.spring_shop.controller.order;

import com.nc13.spring_shop.model.CartDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String insert(@PathVariable int itemId, HttpSession session, OrderDTO orderDTO, RedirectAttributes redirectAttributes) {

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
        orderDTO.setContent(itemDTO.getContent());
        itemDTO.setQuantity(itemDTO.getQuantity() - orderDTO.getQuantity());
        if (itemDTO.getQuantity() < 0) {
            redirectAttributes.addFlashAttribute("message", "재고가 부족합니다");
            return "redirect:/showMessage";
        }

        itemService.updateQuantity(itemDTO);
        orderService.insert(orderDTO);

        return "redirect:/item/showOne/" + orderDTO.getItemId();
    }

    @GetMapping("/showAll/{memberId}")
    public String showAll(@PathVariable int memberId, Model model,
                          HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (memberId != login.getId()) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
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

    @GetMapping("update/{orderId}")
    public String showUpdate(HttpSession session, @PathVariable int orderId,
                             Model model, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        OrderDTO orderDTO = orderService.selectOne(orderId);
        if (orderDTO == null) {
            redirectAttributes.addFlashAttribute("message", "주문이 없습니다.");
            return "redirect:/showMessage";
        }
        ItemDTO itemDTO = itemService.selectOne(orderDTO.getItemId());
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("itemDTO", itemDTO);

        return "/order/update";
    }

    @PostMapping("update/{orderId}")
    public String update(OrderDTO orderDTO, @PathVariable int orderId,
                         HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        OrderDTO updateOrder = orderService.selectOne(orderId);

        if (updateOrder == null) {
            redirectAttributes.addFlashAttribute("message", "주문이 없습니다.");
            return "redirect:/showMessage";
        }

        ItemDTO itemDTO = itemService.selectOne(updateOrder.getItemId());
        itemDTO.setQuantity(itemDTO.getQuantity() + updateOrder.getQuantity() - orderDTO.getQuantity());
        itemService.updateQuantity(itemDTO);

        updateOrder.setQuantity(orderDTO.getQuantity());
        updateOrder.setPrice(orderDTO.getQuantity() * itemDTO.getPrice());
        orderService.update(updateOrder);

        return "redirect:/order/showOne/" + updateOrder.getId();
    }

    @GetMapping("delete/{orderId}")
    public String delete(HttpSession session, @PathVariable int orderId,RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        OrderDTO orderDTO = orderService.selectOne(orderId);
        if (orderDTO == null) {
            redirectAttributes.addFlashAttribute("message", "주문이 없습니다.");
            return "redirect:/showMessage";
        }
        orderService.delete(orderId);
        return "redirect:/order/showAll/" + login.getId();
    }
}
