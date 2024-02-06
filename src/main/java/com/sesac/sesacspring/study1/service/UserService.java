package com.sesac.sesacspring.study1.service;

import com.sesac.sesacspring.study1.domain.User;
import com.sesac.sesacspring.study1.dto.UserDto;
import com.sesac.sesacspring.study1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    // UserMapper 생성하는 방법 1. 생성자 사용
//    private final UserMapper userMapper;
//    public UserService(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }

    // UserMapper 생성하는 방법 2. @Autowired
    @Autowired
    UserMapper userMapper;

    public List<UserDto> retrieveAll(){
        // Controller에서 호출하는 메소드
        // usermapper의 retrieveAll() 실행한 후 받아온 List<User>
        // List<UserDto>에 담아서 반환한다.

        List<User> users = userMapper.retrieveAll();
        List<UserDto> result = new ArrayList<>();

        // for문을 이용한 List<User> -> List<UserDto>
        for(User user: users){
            UserDto userDto = new UserDto();
            userDto.setUser(user.getName());
            userDto.setNickname(user.getNickname());

            result.add(userDto);
        }

        // 1. userService.retrieveAll()에서 의존성을 주입받은 userMapper.retrieveAll() 호출
        // 2. userMapper interface 파일에서 xml파일 확인 필요 여부 체크
        // 3. 정의된 mapper 폴더(application.properties에서 정의)에서 namespace가 UserMapper인 xml
        // 4. id가 retrieveAll인 태그를 찾고 그 안의 sql문을 실행
        // 5. 실행 결과를 resultType에 정의된 객체에 매핑해서 반환
        return result;
    }

    public void createUser(String name, String nickname) {
        System.out.println(name);
        System.out.println(nickname);
        userMapper.createUser( name, nickname);
    }

    public void updateUser(int id, String nickname) {
        userMapper.updateUser(id, nickname);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }

}
