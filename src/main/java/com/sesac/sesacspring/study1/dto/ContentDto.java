package com.sesac.sesacspring.study1.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ContentDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Timestamp registered;
}
