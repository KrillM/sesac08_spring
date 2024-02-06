package com.sesac.sesacspring.study2.controller;

import com.sesac.sesacspring.study2.dto.StudentDto;
import com.sesac.sesacspring.study2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//    @GetMapping("/count")
//    public int getCountAll(){}

    @GetMapping("/all")
    public List<StudentDto> getAll(){
        // student의 목록을 전부 가져와서 보여주는 api
        List<StudentDto> result = studentService.getStudentAll();
        return result;
//        return studentService.getStudentAll();
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}
}