package com.sesac.sesacspring.study1.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Content {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Timestamp registered;
}
