package com.sesac.sesacspring.study2.controller;

import com.sesac.sesacspring.study1.dto.ContentDto;
import com.sesac.sesacspring.study2.dto.StudentDto;
import com.sesac.sesacspring.study2.entity.Student;
import com.sesac.sesacspring.study2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//    @GetMapping("/count")
//    public int getCountAll(){}

    // 1. 전체 검색 (select * from student)
    @GetMapping("/all")
    public List<StudentDto> getAll(){
        // student의 목록을 전부 가져와서 보여주는 api
        List<StudentDto> result = studentService.getStudentAll();
        return result;
//        return studentService.getStudentAll();
    }

    // 2. 삽입 (insert into)
    @GetMapping("/insert")
    public String insertStudent(@RequestParam String name,
                                @RequestParam String nickname,
                                @RequestParam Student.LoginType loginType){
         return studentService.insertStudent(name, nickname, loginType);
    }

    // 3. 검색
    @GetMapping("/search/name")
    public String searchStudentByName(@RequestParam String name){
        return studentService.searchStudentByName(name);
    }

    @GetMapping("/search/id")
    public String searchStudentById(@RequestParam int id){
        return studentService.searchStudentById(id);
    }

    @GetMapping("/search/nickname")
    public String searchStudentByNickname(@RequestParam String nickname){
        return studentService.searchStudentByNickname(nickname);
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @RequestParam String name){
        return studentService.updateStudent(id, name);
    }

}