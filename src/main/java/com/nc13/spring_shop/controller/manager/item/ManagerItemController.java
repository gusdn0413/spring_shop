package com.nc13.spring_shop.controller.manager.item;

import com.nc13.spring_shop.model.ItemDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manager/item/")
@Controller
@RequiredArgsConstructor
public class ManagerItemController {
    private final ItemService itemService;

    @GetMapping("showAll")
    public String moveToFirstPage() {
        return "redirect:/manager/item/showAll/1";
    }

    @GetMapping("showAll/{pageNo}")
    public String showAll(HttpSession session, Model model, @PathVariable int pageNo) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        int maxPage = itemService.selectMaxPage();
        model.addAttribute("maxPage", maxPage);
        int startPage;

        int endPage;

        if (maxPage < 5) {
            startPage = 1;
            endPage = maxPage;
        } else if (pageNo <= 3) {
            startPage = 1;
            endPage = 5;
        } else if (pageNo >= maxPage - 2) {
            startPage = maxPage - 4;
            endPage = maxPage;
        } else {
            startPage = pageNo - 2;
            endPage = pageNo + 2;
        }

        model.addAttribute("curPage", pageNo);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        List<ItemDTO> itemList = itemService.selectAll(pageNo);
        model.addAttribute("itemList", itemList);

        return "manager/item/showAll";
    }

    @GetMapping("delete/{itemId}")
    public String delete(@PathVariable int itemId) {

        itemService.delete(itemId);
        return "redirect:/manager/item/showAll";
    }
}
