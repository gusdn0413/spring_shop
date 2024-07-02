package com.nc13.spring_shop.controller.manager.member;

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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/member/")
public class ManagerMemberController {
    private final MemberService memberService;

    @GetMapping("showAll")
    public String showAll(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        List<MemberDTO> memberList = memberService.selectAll();
        model.addAttribute("memberList", memberList);
        return "manager/member/showAll";
    }

    @GetMapping("showOne/{memberId}")
    public String showOne(@PathVariable int memberId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
        }
        MemberDTO memberDTO = memberService.selectOne(memberId);
        model.addAttribute("memberDTO", memberDTO);
        return "manager/member/showOne";
    }

    @GetMapping("update/{memberId}")
    public String showUpdate(@PathVariable int memberId, HttpSession session,
                             RedirectAttributes redirectAttributes, Model model) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/";
        }
        MemberDTO memberDTO = memberService.selectOne(memberId);
        if (memberDTO == null) {
            redirectAttributes.addFlashAttribute("message", "회원이 없습니다.");
            return "redirect:/";
        }
        model.addAttribute("memberDTO", memberDTO);
        return "manager/member/update";
    }

    @PostMapping("update/{memberId}")
    public String update(@PathVariable int memberId, HttpSession session,
                         MemberDTO memberDTO, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        MemberDTO updateMemberDTO = memberService.selectOne(memberId);
        if (updateMemberDTO == null) {
            redirectAttributes.addFlashAttribute("message", "회원이 없습니다.");
            return "redirect:/showMessage";
        }

        updateMemberDTO.setName(memberDTO.getName());
        updateMemberDTO.setNickname(memberDTO.getNickname());
        updateMemberDTO.setRole(memberDTO.getRole());
        memberService.updateByManager(updateMemberDTO);

        return "redirect:/manager/member/showOne/" + memberId;
    }

    @GetMapping("delete/{memberId}")
    public String delete(@PathVariable int memberId, HttpSession session, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() != 3) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        memberService.delete(memberId);
        return "redirect:/manager/member/showAll";
    }
}
