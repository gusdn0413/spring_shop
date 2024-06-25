package com.nc13.spring_shop.controller.member;

import com.nc13.spring_shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/validateEmail")
    public Map<String, Object> validateUsername(String email) {

        HashMap<String , Object> resultMap = new HashMap<>();
        boolean result = memberService.validateEmail(email);

        if (result) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");

        }
        return resultMap;
    }
}
