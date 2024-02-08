package com.sesac.sesacspring.study2.repository;

import com.sesac.sesacspring.study2.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 1. jpa 기본 규칙을 따르는 방법
    //    Student findByName(String name);
    List<Student> findByName(String name);
    List<Student> findByNameAndNickname(String name, String nickname);
    List<Student> findByNameOrNickname(String name, String nickname);
//    findByAgeGreaterThanEqual(int age);

    // 2. 직접 쿼리를 작성하여 연결
//    @Query("select s from Student s where s.name = :name")
    @Query(nativeQuery = true, value="select * from Student where name = :name")
    List<Student> findTest(String name);

    int countByNickname(String nickname);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :name WHERE s.id = :id")
    void updateStudentNameById(@Param("id") int id, @Param("name") String name);
}
