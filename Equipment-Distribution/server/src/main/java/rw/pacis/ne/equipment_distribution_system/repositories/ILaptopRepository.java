package rw.pacis.ne.equipment_distribution_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.pacis.ne.equipment_distribution_system.models.Laptop;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILaptopRepository extends JpaRepository<Laptop, UUID> {
    Optional<Laptop> findBySerialNumber(String serialNumber);
}
