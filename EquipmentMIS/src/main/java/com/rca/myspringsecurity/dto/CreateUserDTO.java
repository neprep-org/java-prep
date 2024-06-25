package com.rca.myspringsecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CreateUserDTO {
    @Pattern(regexp="^[A-Za-z]*$", message = "Invalid Input")
    @NotEmpty(message = "Name is required!")
    private String name;
    @NotEmpty(message = "Email is required!")
    private String email;
    @NotEmpty(message = "password is required!")
    private String password;
    @NotEmpty(message = "Role(s) is required!")
    private String roles;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }
}
