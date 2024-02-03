package com.sesac.sesacspring.mapper;

import com.sesac.sesacspring.domain.Content;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    List<Content> writeAll();

    @Insert("insert into content(title, content, writer) values (#{title}, #{content}, #{writer})")
    void createContent(String title, String content, String writer);

    @Delete("delete from content where id={#id}")
    void deleteContent(int id);
}
