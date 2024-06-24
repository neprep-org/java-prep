package rw.pacis.ne.equipment_distribution_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateEmployeeDTO;
import rw.pacis.ne.equipment_distribution_system.models.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {

    Employee findById(UUID employeeId);

    Page<Employee> getEmployeesPaginated(Pageable pageable);

    List<Employee> getEmployees();

    Employee addNewEmployee(CreateOrUpdateEmployeeDTO dto);
}
