package com.sesac.sesacspring.study2.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentDto {
    private String name;
    private String nickname;
}
