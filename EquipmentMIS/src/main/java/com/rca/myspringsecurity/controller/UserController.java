package com.rca.myspringsecurity.controller;

import com.rca.myspringsecurity.dto.CreateUserDTO;
import com.rca.myspringsecurity.entity.AuthRequest;
import com.rca.myspringsecurity.entity.UserData;
import com.rca.myspringsecurity.service.JwtService;
import com.rca.myspringsecurity.service.UserDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDataService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody @Valid CreateUserDTO userDTO) {
        UserData userData = new UserData();
        userData.setName(userDTO.getName());
        userData.setEmail(userDTO.getEmail());
        userData.setPassword(userDTO.getPassword());
        userData.setRoles(userDTO.getRoles());
        return service.addUser(userData);
    }
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
    @GetMapping("/admin/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserData> listUsers() {
        return service.listAll();
    }
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserData getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

