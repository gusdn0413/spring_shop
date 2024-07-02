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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item/")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("showAllByCategory/{categoryId}")
    public String moveToFirstPageByCategory(@PathVariable int categoryId) {
        return "redirect:/item/showAllByCategory/1/" + categoryId;
    }

    @GetMapping("showAllByCategory/{pageNo}/{categoryId}")
    public String showAllByCategory(@PathVariable int pageNo,@PathVariable int categoryId,
                                    HttpSession session, Model model) {

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



        List<ItemDTO> itemList = itemService.selectAllByCategory(pageNo,categoryId);
        model.addAttribute("itemList", itemList);

        return "/item/showAll";
    }

    @GetMapping("showAll")
    public String moveToFirstPage() {
        return "redirect:/item/showAll/1";
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

        // 끝 페이지
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

        return "/item/showAll";
    }

    @GetMapping("showOne/{itemId}")
    public String showOne(@PathVariable int itemId, HttpSession session,
                          Model model, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        ItemDTO itemDTO = itemService.selectOne(itemId);
        if (itemDTO == null) {
            redirectAttributes.addFlashAttribute("message", "상품이 없습니다.");
            return "redirect:/showMessage";
        }

        model.addAttribute("itemDTO", itemDTO);

        return "item/showOne";
    }
}
