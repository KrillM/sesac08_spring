package com.sesac.sesacspring.study2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 빈 생성자가 필수!
@NoArgsConstructor // @NoArgsConstructor 먼저 적고 @AllArgsConstructor 나중에 적는다.
@Getter
@Setter
@Builder // 필드 전체가 들어있는 생성자가 필수
@AllArgsConstructor // entity와 builder 둘 다 사용이 가능하다
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // SEQUENCE: 새로운 오브젝트를 만들어서 id를 부여하는 방법
    // TABLE: SEQUENCE 전략을 흉내낸 전략 -> 모든 DBMS에서 사용이 가능하다

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String nickname;

    // enum
    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    public enum LoginType{
        GOOGLE, META
    }


}
