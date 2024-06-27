package com.nc13.spring_shop.controller.cart;

import com.nc13.spring_shop.model.CartDTO;
import com.nc13.spring_shop.model.ItemDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.CartService;
import com.nc13.spring_shop.service.ItemService;
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
@RequestMapping("/cart/")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;

    @GetMapping("insert/{itemId}")
    public String showInsert(HttpSession session, @PathVariable int itemId, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        ItemDTO itemDTO = itemService.selectOne(itemId);
        model.addAttribute("itemDTO", itemDTO);
        return "/cart/insert";
    }

    @PostMapping("insert/{itemId}")
    public String insert(HttpSession session, @PathVariable int itemId, CartDTO cartDTO) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        ItemDTO itemDTO = itemService.selectOne(itemId);
        cartDTO.setMemberSellerId(itemDTO.getMemberSellerId());
        cartDTO.setMemberCustomerId(login.getId());
        cartDTO.setItemId(itemDTO.getId());
        cartDTO.setPrice(itemDTO.getPrice() * cartDTO.getQuantity());
        cartDTO.setName(itemDTO.getName());

        cartService.insert(cartDTO);

        return "redirect:/item/showOne/" + itemDTO.getId();
    }

    @GetMapping("showAll/{memberId}")
    public String showAll(HttpSession session, @PathVariable int memberId, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        List<CartDTO> cartList = cartService.selectAll(memberId);
        model.addAttribute("cartList", cartList);
        return "/cart/showAll";
    }

    @GetMapping("showOne/{cartId}")
    public String showOne(HttpSession session, @PathVariable int cartId, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        CartDTO cartDTO = cartService.selectOne(cartId);
        if (cartDTO == null) {
            return "redirect:/cart/showAll/" + login.getId();
        }
        model.addAttribute("cartDTO", cartDTO);
        return "/cart/showOne";
    }

    @GetMapping("update/{cartId}")
    public String showUpdate(HttpSession session, @PathVariable int cartId,Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        CartDTO cartDTO = cartService.selectOne(cartId);
        if (cartDTO == null) {
            return "redirect:/cart/showAll/" + login.getId();
        }
        ItemDTO itemDTO = itemService.selectOne(cartDTO.getItemId());
        model.addAttribute("cartDTO", cartDTO);
        model.addAttribute("itemDTO", itemDTO);

        return "/cart/update";
    }

    @PostMapping("update/{cartId}")
    public String update(CartDTO cartDTO, @PathVariable int cartId) {

        CartDTO updateCart = cartService.selectOne(cartId);

        ItemDTO itemDTO = itemService.selectOne(updateCart.getItemId());
        updateCart.setQuantity(cartDTO.getQuantity());
        updateCart.setPrice(cartDTO.getQuantity() * itemDTO.getPrice());
        cartService.update(updateCart);

        return "redirect:/cart/showOne/" + updateCart.getId();
    }

    @GetMapping("delete/{cartId}")
    public String delete(HttpSession session, @PathVariable int cartId) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        CartDTO cartDTO = cartService.selectOne(cartId);
        if (cartDTO == null) {
            return "redirect:/cart/showAll/" + login.getId();
        }
        cartService.delete(cartId);
        return "redirect:/cart/showAll/" + login.getId();
    }
}
