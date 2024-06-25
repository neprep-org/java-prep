package com.rca.myspringsecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateLaptopDTO {
    @NotEmpty(message = "Brand is required!")
    String brand;
    @NotEmpty(message = "Serial Number is required!")
    String sn;
    @NotEmpty(message = "Student Id is required!")
    Integer studentId;

}
