package com.nc13.spring_shop.controller.item;

import com.nc13.spring_shop.model.ItemDTO;
import com.nc13.spring_shop.model.MemberDTO;
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
@RequestMapping("/item/")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("showOne/{itemId}")
    public String showOne(@PathVariable int itemId, HttpSession session, Model model) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        ItemDTO itemDTO = itemService.selectOne(itemId);
        if (itemDTO == null) {
            return "redirect:/item/showAll";
        }
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("login", login);
        return "item/showOne";
    }

    @GetMapping("showAllByCategory/{categoryId}")
    public String showAllByCategory(@PathVariable int categoryId, HttpSession session, Model model) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }

        List<ItemDTO> itemList = itemService.selectAllByCategory(categoryId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("itemList", itemList);

        return "/item/showAll";
    }

    @GetMapping("showAll")
    public String showAll(HttpSession session, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        List<ItemDTO> itemList = itemService.selectAll();
        model.addAttribute("itemList", itemList);

        return "/item/showAll";
    }
}
