package com.nc13.spring_shop.controller.item.seller;

import com.nc13.spring_shop.model.ItemDTO;
import com.nc13.spring_shop.model.MemberDTO;
import com.nc13.spring_shop.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item/seller/")
public class ItemSellerController {

    private final ItemService itemService;

    @GetMapping("insert/{categoryId}")
    public String showInsert(@PathVariable int categoryId, HttpSession session,
                             Model model, RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }
        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        model.addAttribute("categoryId", categoryId);
        return "item/seller/insert";
    }

    @PostMapping("insert/{categoryId}")
    public String insert(@PathVariable int categoryId, HttpSession session,
                         ItemDTO itemDTO, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        itemDTO.setMemberSellerId(login.getId());
        itemDTO.setCategoryId(categoryId);
        itemService.insert(itemDTO);

        return "redirect:/item/showOne/" + itemDTO.getId();
    }

    @GetMapping("showAll/{memberId}")
    public String showAllBySeller(@PathVariable int memberId, HttpSession session,
                                  Model model, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }

        if (login.getRole() != 2) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        List<ItemDTO> itemList = itemService.selectAllBySellerId(memberId);
        model.addAttribute("itemList", itemList);
        return "item/showAll";
    }

    @GetMapping("update/{itemId}")
    public String showUpdate(@PathVariable int itemId, HttpSession session,
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

        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }
        model.addAttribute("itemDTO", itemDTO);
        return "item/seller/update";
    }

    @PostMapping("update/{itemId}")
    public String update(@PathVariable int itemId, ItemDTO itemDTO,
                         HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO login = (MemberDTO) session.getAttribute("login");
        if (login == null) {
            return "redirect:/";
        }

        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        itemDTO.setId(itemId);

        itemService.update(itemDTO);
        return "redirect:/item/showOne/" + itemDTO.getId();
    }

    @GetMapping("delete/{itemId}")
    public String delete(@PathVariable int itemId, HttpSession session,
                         RedirectAttributes redirectAttributes) {

        MemberDTO login = (MemberDTO) session.getAttribute("login");

        if (login == null) {
            return "redirect:/";
        }
        ItemDTO itemDTO = itemService.selectOne(itemId);
        if (itemDTO == null) {
            redirectAttributes.addFlashAttribute("message", "상품이 없습니다.");
            return "redirect:/showMessage";
        }

        if (login.getRole() == 1) {
            redirectAttributes.addFlashAttribute("message", "권한이 없습니다.");
            return "redirect:/showMessage";
        }

        itemService.delete(itemId);
        return "redirect:/item/seller/showAll/" + login.getId();
    }

    @ResponseBody
    @PostMapping("uploads")
    public Map<String, Object> uploads(MultipartHttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();

        String uploadPath = "";

        MultipartFile file = request.getFile("upload");
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String uploadName = UUID.randomUUID() + extension;

        String realPath = request.getServletContext().getRealPath("/item/uploads/");
        Path realDir = Paths.get(realPath);

        if (!Files.exists(realDir)) {
            try {
                Files.createDirectories(realDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File uploadFile = new File(realPath + uploadName);

        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            System.out.println("파일 전송 중 에러");
            e.printStackTrace();
        }

        uploadPath = "/item/uploads/" + uploadName;

        resultMap.put("uploaded", true);
        resultMap.put("url", uploadPath);

        return resultMap;
    }
}