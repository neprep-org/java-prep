package rw.pacis.ne.equipment_distribution_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.pacis.ne.equipment_distribution_system.models.EmployeeLaptop;
import rw.pacis.ne.equipment_distribution_system.models.Laptop;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeLaptopRepository extends JpaRepository<EmployeeLaptop, UUID> {

    Optional<EmployeeLaptop> findByLaptop(Laptop laptop);

}
