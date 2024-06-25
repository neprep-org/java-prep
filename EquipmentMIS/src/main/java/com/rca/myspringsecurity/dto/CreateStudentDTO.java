package com.rca.myspringsecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateStudentDTO {
    @NotEmpty(message = "First Name is required!")
    private String firstName;
    @NotEmpty(message = "Last Name is required!")
    private String lastName;
    @NotEmpty(message = "Email is required!")
    private String email;

}
