package com.sesac.sesacspring.study2.service;

import com.sesac.sesacspring.study2.dto.StudentDto;
import com.sesac.sesacspring.study2.entity.Student;
import com.sesac.sesacspring.study2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String insertStudent(String name, String nickname, Student.LoginType loginType) {
        // 받아온 데이터로 repository의 save 메소드 호출
        Student student = Student.builder().name(name).nickname(nickname).loginType(loginType).build();
        Student newStudent = studentRepository.save(student);
        // newStudent: save한 후 반환되는 Entity 객체

        return newStudent.getId() + newStudent.getName();
    }

    public String searchStudentByName(String name) {
        List<Student> student = studentRepository.findByName(name);
        return "해당하는 이름의 사용자는 " + student.size() + "명 입니다.";
    }

    public String searchStudentById(int id) {
//        Student student = studentRepository.findById(id);
//        Optional<Student> student = studentRepository.findById(id);
//        if(student.isPresent()){
//            // isPresent: 객체의 여부를 boolean으로 반환
//            return student.get().getName();
//            // get: Optional에 담긴 객체를 반환
//        }
//        return "null";
//
        // Optional<T>: java 8부터 등장
        // null일수도 있는 객체를 감싸는 wrapper 클래스

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No User"));
//        orElse(): 없으면 다른 값 반환, orElseThrow(): 없으면 error 처리
        return student.getName();
    }

    public String searchStudentByNickname(String nickname) {
        int count = studentRepository.countByNickname(nickname);
        return "해당하는 이름의 사용자는 " + count + "명 입니다.";
    }

    public String updateStudent(int id, String name) {
        studentRepository.updateStudentNameById(id, name);
        Student updatedStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));
        return updatedStudent.getId() + updatedStudent.getName();
    }
}
