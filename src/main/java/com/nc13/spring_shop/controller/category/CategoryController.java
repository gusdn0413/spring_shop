package com.nc13.spring_shop.controller.category;

import com.nc13.spring_shop.model.CategoryDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("insert")
    public String showInsert(HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        return "category/manager/insert";
    }

    @PostMapping("insert")
    public String insert(CategoryDTO categoryDTO) {
        categoryService.insert(categoryDTO);
        return "redirect:/shop/showAll";
    }

    @GetMapping("showAll")
    public String showAll(HttpSession session, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        List<CategoryDTO> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        return "category/showAll";
    }
}
