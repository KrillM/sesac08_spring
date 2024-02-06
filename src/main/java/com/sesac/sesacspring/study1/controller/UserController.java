package com.sesac.sesacspring.study1.controller;

import com.sesac.sesacspring.study1.dto.UserDto;
import com.sesac.sesacspring.study1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    // 테이블 -> 도메인 -> mapper -> Service -> controller -> View

    @Autowired
    UserService userService;

    @GetMapping("/all") // localhost:8080/user/all
    public List<UserDto> getUser(){
        List<UserDto> result = userService.retrieveAll();
        return result;
    } // []

    @GetMapping("/user")
    public void getUserInsert(@RequestParam String user, @RequestParam String nickname){
        userService.createUser(user, nickname);
    }

    @PostMapping("/update")
    public void getUserUpdate(@RequestParam int id, @RequestParam String nickname){
        userService.updateUser(id, nickname);
    }

    @GetMapping("/delete")
    public void getUserUpdate(@RequestParam int id){
        userService.deleteUser(id);
    }
}
