package com.rca.myspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rca.myspringsecurity.entity.Student;
import com.rca.myspringsecurity.repository.StudentRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;
    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void addStudent(Student st) {
        repo.save(st);
    }
    public Optional<Student> getStudentById(Integer Id){
        return repo.findById(Id);
    }
    public List<Student> getStudentsSortedByFirstName() {
        List<Student> students = repo.findAll();
        Collections.sort(students, Comparator.comparing(Student::getFirstName));
        return students;
    }


}

