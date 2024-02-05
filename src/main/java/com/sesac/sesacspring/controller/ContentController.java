package com.sesac.sesacspring.controller;

import com.sesac.sesacspring.dto.ContentDto;
import com.sesac.sesacspring.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    @GetMapping("/write") // localhost:8080/write
    public String contentPage(Model model){
        List<ContentDto> contents = contentService.writeAll();
        model.addAttribute("contents", contents);
        return "pr04";
    }

    @PostMapping("/writing")
    public String insertContent(@RequestParam String title, @RequestParam String content, @RequestParam String writer){
        contentService.createContent(title, content, writer);
        return "redirect:/write";
    }

    @PostMapping("/delete/{id}")
    public String deleteContent(@PathVariable("id") int id){
        contentService.deleteContent(id);
        return "redirect:/write";
    }

}
