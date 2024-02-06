package com.sesac.sesacspring.study1.mapper;

import com.sesac.sesacspring.study1.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // sql문을 정의하거나 xml 파일을 읽는다
    List<User> retrieveAll(); // xml 파일을 읽어서 실행한다

    // sql문 정의
    @Insert("insert into user(name, nickname) values(#{name} ,#{nickname})")
    void createUser(String name, String nickname);

    @Update("UPDATE user SET nickname = #{nickname} WHERE id=#{id}")
    void updateUser(int id, String nickname);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteUser(int id);

}
