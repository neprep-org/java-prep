package rw.pacis.ne.equipment_distribution_system.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.pacis.ne.equipment_distribution_system.audits.InitiatorAudit;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateEmployeeDTO;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(name = "national_id",unique = true)
    private String nationalId;

    @Column(name = "phone_number",unique = true)
    private String phoneNumber;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    public Employee(CreateOrUpdateEmployeeDTO dto) {
        this.firstname = dto.getFirstname();
        this.lastname = dto.getLastname();
        this.nationalId = dto.getNationalId();
        this.phoneNumber = dto.getPhoneNumber();
        this.email = dto.getEmail();
        this.department = dto.getDepartment();
        this.position = dto.getPosition();
    }
}
