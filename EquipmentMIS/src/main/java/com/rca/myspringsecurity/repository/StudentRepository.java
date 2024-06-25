package com.rca.myspringsecurity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rca.myspringsecurity.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s where s.email= ?1")
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentsById(Integer id);
}
