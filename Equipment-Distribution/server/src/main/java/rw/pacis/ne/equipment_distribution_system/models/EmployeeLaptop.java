package rw.pacis.ne.equipment_distribution_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.pacis.ne.equipment_distribution_system.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_laptop")
public class EmployeeLaptop extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
