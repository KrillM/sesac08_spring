package com.sesac.sesacspring.study1.controller;

import com.sesac.sesacspring.study1.dto.ContentDto;
import com.sesac.sesacspring.study1.service.ContentService;
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

    @PostMapping("/update/{id}")
    public String updateContent(@PathVariable("id") int id, @RequestBody ContentDto contentDto){
        contentService.updateContent(id, contentDto.getTitle(), contentDto.getContent(), contentDto.getWriter());
        return "redirect:/write";
    }

    @PostMapping("/delete/{id}")
    public String deleteContent(@PathVariable("id") int id){
        contentService.deleteContent(id);
        return "redirect:/write";
    }

}
