package com.sesac.sesacspring.study1.service;

import com.sesac.sesacspring.study1.domain.Content;
import com.sesac.sesacspring.study1.dto.ContentDto;
import com.sesac.sesacspring.study1.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    ContentMapper contentMapper;

    public List<ContentDto> writeAll() {
        List<Content> contents = contentMapper.writeAll();
        List<ContentDto> result = new ArrayList<>();

        for(Content content: contents) {
            ContentDto contentDto = new ContentDto();
            contentDto.setId(content.getId());
            contentDto.setTitle(content.getTitle());
            contentDto.setContent(content.getContent());
            contentDto.setWriter(content.getWriter());
            contentDto.setRegistered(content.getRegistered());
            result.add(contentDto);
        }
        return result;
    }

    public void createContent(String title, String content, String writer){
        contentMapper.createContent(title, content, writer);
    }

    public void updateContent(int id, String title, String content, String writer) {
        contentMapper.updateContent(id, title, content, writer);
    }

    public void deleteContent(int id) {
        contentMapper.deleteContent(id);
    }
}
