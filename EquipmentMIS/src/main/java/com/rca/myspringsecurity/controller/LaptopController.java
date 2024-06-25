package com.rca.myspringsecurity.controller;

import com.rca.myspringsecurity.dto.CreateLaptopDTO;
import com.rca.myspringsecurity.entity.Laptop;
import com.rca.myspringsecurity.entity.Student;
import com.rca.myspringsecurity.entity.UserData;
import com.rca.myspringsecurity.repository.LaptopRepository;
import com.rca.myspringsecurity.service.JwtService;
import com.rca.myspringsecurity.service.LaptopService;
import com.rca.myspringsecurity.service.StudentService;
import com.rca.myspringsecurity.service.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptops")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/registration")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addLaptop(@RequestBody CreateLaptopDTO dto, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        UserData info = userDataService.loadCurrentUser(username);
        Student student = studentService.getStudentById(dto.getStudentId()).orElseThrow(() -> new UsernameNotFoundException("Student not found"));
        Laptop laptop = new Laptop();
        laptop.setSn(dto.getSn());
        laptop.setBrand(dto.getBrand());
        laptop.setStudent(student);
        laptopService.addLaptop(laptop);
    }

    @GetMapping("/info")
    public String info() {
        return "Amazing day";
    }


}
