package com.sesac.sesacspring.study1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello") // URL 주소
    public String getHi(Model model){ // 제어의 역전 덕분에 Model을 직접 작성하지 않는다
        model.addAttribute("msg", "Spring Boot and Thymeleaf"); // ${msg} 통해 보여줄 내용
        model.addAttribute("msg2", "<strong>Man</strong>"); // ${msg} 통해 보여줄 내용

        model.addAttribute("position", "mid-fielder");
        model.addAttribute("age", 16);

        String[]crews = {"크릴", "예나", "지수", "수아", "이든"};
        model.addAttribute("crews", crews);

        return "hi"; // templates 이름
    }
}
