package com.sesac.sesacspring.study2.dto;

public class StudentDtoBuilder {
    private final String name;
    private final String nickname;

    public static class Builder{
        private String name;
        private String nickname;
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder nickname(String nickname){
            this.nickname = nickname;
            return this;
        }
        public StudentDtoBuilder build(){
            return new StudentDtoBuilder(name, nickname);
        }
    }

    public StudentDtoBuilder(String name, String nickname){
        this.name = name;
        this.nickname = nickname;
    }
}
