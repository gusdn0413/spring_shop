package com.nc13.spring_shop.controller.category.manager;

import com.nc13.spring_shop.model.CategoryDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/category/manager/")
@RequiredArgsConstructor
@Controller
public class CategoryManagerController {

    private final CategoryService categoryService;

    @GetMapping("update/{categoryId}")
    public String showUpdate(@PathVariable int categoryId, HttpSession session,
                             Model model, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다");
        }
        CategoryDTO categoryDTO = categoryService.selectOne(categoryId);
        if (categoryDTO == null) {
            redirectAttributes.addFlashAttribute("message", "카테고리가 없습니다.");
        }
        model.addAttribute("categoryDTO", categoryDTO);
        return "category/manager/update";
    }

    @PostMapping("update/{categoryId}")
    public String update(@PathVariable int categoryId, CategoryDTO categoryDTO,
                         HttpSession session, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다");
        }

        CategoryDTO updateCategoryDTO = categoryService.selectOne(categoryId);
        if (updateCategoryDTO == null) {
            redirectAttributes.addFlashAttribute("message", "카테고리가 없습니다.");
        }

        updateCategoryDTO.setName(categoryDTO.getName());
        categoryService.update(updateCategoryDTO);
        return "redirect:/category/showAll";
    }

    @GetMapping("delete/{categoryId}")
    public String delete(@PathVariable int categoryId, HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다");
        }

        CategoryDTO updateCategoryDTO = categoryService.selectOne(categoryId);
        if (updateCategoryDTO == null) {
            redirectAttributes.addFlashAttribute("message", "카테고리가 없습니다.");
        }
        categoryService.delete(categoryId);
        return "redirect:/category/showAll";
    }
}
