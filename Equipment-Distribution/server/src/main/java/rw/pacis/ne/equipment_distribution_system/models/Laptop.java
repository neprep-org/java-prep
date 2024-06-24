package rw.pacis.ne.equipment_distribution_system.models;

import lombok.*;
import rw.pacis.ne.equipment_distribution_system.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "laptops")
public class Laptop extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column
    private String laptopManufacturer;

    @Column
    private String model;

    @Column(name = "email",unique = true)
    private String serialNumber;
}
