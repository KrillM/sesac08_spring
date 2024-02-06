package com.sesac.sesacspring.study2.service;

import com.sesac.sesacspring.study2.dto.StudentDto;
import com.sesac.sesacspring.study2.entity.Student;
import com.sesac.sesacspring.study2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDto> getStudentAll(){
         List<Student> result =  studentRepository.findAll();
         List<StudentDto> students = new ArrayList<>();

         for(Student student : result){
             // Builder: 객체를 생성할 때 순서 또는 명시적이지 못한 문제를 해결하기 위해 나왔다.
             // 생성자 주입: 여러 개의 필두가 있을 때 순서를 지켜야 한다.
             // setter: 필드 개수만큼 매번 메소드를 만들어줘야 한다.

             StudentDto studentDto = StudentDto.builder()
                     .name(student.getName())
                     .nickname(student.getNickname())
                     .build();
             students.add(studentDto);
         }
         return students;
    }
}
