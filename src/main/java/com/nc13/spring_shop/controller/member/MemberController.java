package com.nc13.spring_shop.controller.member;

import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/member/")
@Controller
public class MemberController {

    private final MemberService memberService;

    @PostMapping("auth")
    public String auth(MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO login = memberService.auth(memberDTO);
        if (login != null) {
            session.setAttribute("login", login);
            return "redirect:/shop/showAll";
        }
        model.addAttribute("loginFailed", true);
        return "index";
    }

    @GetMapping("register")
    public String showRegister() {
        return "member/register";
    }

    @PostMapping("register")
    public String register(MemberDTO memberDTO) {
        memberService.register(memberDTO);
        return "redirect:/";
    }

    @GetMapping("showOne")
    public String showOne(Model model, HttpSession session) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }

        MemberDTO memberDTO = memberService.selectOne(login.getId());
        model.addAttribute("memberDTO", memberDTO);

        return "member/showOne";
    }

    @GetMapping("update/{memberId}")
    public String showUpdate(@PathVariable int memberId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        MemberDTO memberDTO = memberService.selectOne(memberId);

        model.addAttribute("memberDTO", memberDTO);
        return "member/update";

    }

    @PostMapping("update/{memberId}")
    public String update(@PathVariable int memberId, HttpSession session, MemberDTO memberDTO,
                         RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        if (memberId != login.getId()) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        memberDTO.setId(memberId);
        System.out.println(memberDTO);
        memberService.update(memberDTO);

        return "shop/showAll";
    }

    @GetMapping("delete/{memberId}")
    public String delete(@PathVariable int memberId, HttpSession session) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        memberService.delete(memberId);
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
