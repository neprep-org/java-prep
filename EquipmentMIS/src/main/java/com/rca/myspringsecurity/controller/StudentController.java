package com.rca.myspringsecurity.controller;
import com.rca.myspringsecurity.dto.CreateStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rca.myspringsecurity.entity.Student;
import com.rca.myspringsecurity.entity.UserData;
import com.rca.myspringsecurity.service.JwtService;
import com.rca.myspringsecurity.service.StudentService;
import com.rca.myspringsecurity.service.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/academics")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserDataService userServices;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/registration")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addStudent(@RequestBody CreateStudentDTO student, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        UserData info=userServices.loadCurrentUser(username);
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setCreated(info);
        studentService.addStudent(student1);
    }
    @GetMapping("/info")
    public String info() {
        return "Amazing day";
    }
}

