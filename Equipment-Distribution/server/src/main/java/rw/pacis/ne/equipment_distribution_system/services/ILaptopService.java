package rw.pacis.ne.equipment_distribution_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateLaptopDTO;
import rw.pacis.ne.equipment_distribution_system.models.Laptop;

import java.util.List;
import java.util.UUID;

public interface ILaptopService {

    Page<Laptop> getLaptopsPaginated(Pageable pageable);

    Laptop findById(UUID laptopId);

    List<Laptop> getLaptops();

    Laptop addNewLaptop(CreateOrUpdateLaptopDTO dto);
}
