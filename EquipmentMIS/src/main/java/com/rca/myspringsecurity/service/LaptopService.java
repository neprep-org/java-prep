package com.rca.myspringsecurity.service;

import com.rca.myspringsecurity.entity.Laptop;
import com.rca.myspringsecurity.entity.Student;
import com.rca.myspringsecurity.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopService {

    private final StudentService studentService;

    @Autowired
    private LaptopRepository laptopRepository;

    public LaptopService(StudentService studentService, LaptopRepository laptopRepository) {
        this.studentService = studentService;
        this.laptopRepository = laptopRepository;
    }

    public Laptop addLaptop(Laptop lap){
         return laptopRepository.save(lap);

    }

}

