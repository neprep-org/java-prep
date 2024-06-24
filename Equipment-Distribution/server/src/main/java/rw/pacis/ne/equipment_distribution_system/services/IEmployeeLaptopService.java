package rw.pacis.ne.equipment_distribution_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.pacis.ne.equipment_distribution_system.dtos.NewEmployeeLaptopDTO;
import rw.pacis.ne.equipment_distribution_system.models.EmployeeLaptop;

public interface IEmployeeLaptopService {

    Page<EmployeeLaptop> getEmployeeLaptops(Pageable pageable);

    EmployeeLaptop addNewEmployeeLaptop(NewEmployeeLaptopDTO dto);

}
