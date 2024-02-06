package com.sesac.sesacspring.study1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class Pr01Controller {
    @GetMapping("/pr01")
    public String getPractice01(Model model){
        model.addAttribute("age", 17);
        return "pr01";
    }

    @GetMapping("/people")
    public String getPractice02(Model model){
        ArrayList<Person> people=new ArrayList<>();
        people.add(new Person("크릴", 29));
        people.add(new Person("예나", 22));
        people.add(new Person("지수", 17));
        people.add(new Person("수아", 25));
        people.add(new Person("이든", 33));

        model.addAttribute("people", people);
        return "pr02";
    }
}
