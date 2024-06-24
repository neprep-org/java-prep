package rw.pacis.ne.equipment_distribution_system.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreateOrUpdateEmployeeDTO {

    @NotBlank
    private  String firstname;

    @NotBlank
    private  String lastname;

    @Pattern(regexp = "[0-9]{16}")
    private String nationalId;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,10}", message = "Your phone is not a valid tel we expect 07***")
    private  String phoneNumber;

    @Email
    private  String email;

    @NotBlank
    private  String department;

    @NotBlank
    private  String position;

}
