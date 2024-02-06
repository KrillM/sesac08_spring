package com.sesac.sesacspring.study1.controller;

import com.sesac.sesacspring.study1.vo.PrVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class Pr03Controller {

    @GetMapping("/introduce1/{name}")
    public String gildong1(@PathVariable (value = "name") String irum, Model model){
        model.addAttribute("irum", irum);
        return "pr03";
    }

    @GetMapping("/introduce2")
    public String gildong2(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "pr03";
    }

    @GetMapping("/practice")
    public String practicePage(){
        return "pr03a";
    }

    @PostMapping("/introduce3")
    public String myInfo(@RequestParam(value = "name") String name,
                         @RequestParam(value = "age") String age,
                         @RequestParam(value = "year") String year,
                         @RequestParam(value = "month") String month,
                         @RequestParam(value = "day") String day,
                         @RequestParam(required = false, value = "hobby") String[] hobby,
                         Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("hobby", hobby);
        return "pr03";
    }

    @PostMapping("/introduce4")
    @ResponseBody
    public String myInfoVo(@RequestBody PrVo prVo){
        return "이름: " +prVo.getName()+ "\n성별: " +prVo.getAge() + "\n" +
                "생일: " +prVo.getYear()+ "년 " + prVo.getMonth() + "월 " + prVo.getDay()+"일" +
                "\n취미: " + Arrays.toString(prVo.getHobby()) +"\n" +
                prVo.getName()+" 회원가입 성공!";
    }
}
