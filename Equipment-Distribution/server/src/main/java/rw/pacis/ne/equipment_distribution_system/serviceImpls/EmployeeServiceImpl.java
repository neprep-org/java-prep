package rw.pacis.ne.equipment_distribution_system.serviceImpls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateEmployeeDTO;
import rw.pacis.ne.equipment_distribution_system.exceptions.BadRequestException;
import rw.pacis.ne.equipment_distribution_system.exceptions.ResourceNotFoundException;
import rw.pacis.ne.equipment_distribution_system.models.Employee;
import rw.pacis.ne.equipment_distribution_system.repositories.IEmployeeRepository;
import rw.pacis.ne.equipment_distribution_system.services.IEmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository repository;

    public EmployeeServiceImpl(IEmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(UUID employeeId){
        return repository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee","id",employeeId));
    }

    @Override
    public Page<Employee> getEmployeesPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee addNewEmployee(CreateOrUpdateEmployeeDTO dto) {

        Optional<Employee> findByEmail = repository.findByEmail(dto.getEmail());
        if(findByEmail.isPresent()) throw new BadRequestException(String.format("Employee with email ' %s ' already exists", dto.getEmail()));

        Optional<Employee> findByPhone = repository.findByPhoneNumber(dto.getPhoneNumber());
        if(findByPhone.isPresent()) throw new BadRequestException(String.format("Employee with phone number ' %s ' already exists", dto.getPhoneNumber()));

        Optional<Employee> findByNationalId = repository.findByNationalId(dto.getNationalId());
        if(findByNationalId.isPresent()) throw new BadRequestException(String.format("Employee with national id ' %s ' already exists", dto.getNationalId()));

        Employee employee = new Employee(dto);

        return repository.save(employee);
    }
}
