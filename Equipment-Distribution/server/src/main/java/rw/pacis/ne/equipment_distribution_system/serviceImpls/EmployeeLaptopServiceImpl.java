package rw.pacis.ne.equipment_distribution_system.serviceImpls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.pacis.ne.equipment_distribution_system.dtos.NewEmployeeLaptopDTO;
import rw.pacis.ne.equipment_distribution_system.exceptions.BadRequestException;
import rw.pacis.ne.equipment_distribution_system.models.Employee;
import rw.pacis.ne.equipment_distribution_system.models.EmployeeLaptop;
import rw.pacis.ne.equipment_distribution_system.models.Laptop;
import rw.pacis.ne.equipment_distribution_system.repositories.IEmployeeLaptopRepository;
import rw.pacis.ne.equipment_distribution_system.services.IEmployeeLaptopService;
import rw.pacis.ne.equipment_distribution_system.services.IEmployeeService;
import rw.pacis.ne.equipment_distribution_system.services.ILaptopService;

import java.util.Optional;

@Service
public class EmployeeLaptopServiceImpl implements IEmployeeLaptopService {

    private final IEmployeeLaptopRepository repository;

    private final IEmployeeService employeeService;

    private final ILaptopService laptopService;

    public EmployeeLaptopServiceImpl(IEmployeeLaptopRepository repository, IEmployeeService employeeService, ILaptopService laptopService) {
        this.repository = repository;
        this.employeeService = employeeService;
        this.laptopService = laptopService;
    }

    @Override
    public Page<EmployeeLaptop> getEmployeeLaptops(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public EmployeeLaptop addNewEmployeeLaptop(NewEmployeeLaptopDTO dto) {

        Employee employee = employeeService.findById(dto.getEmployeeId());

        Laptop laptop = laptopService.findById(dto.getLaptopId());

        Optional<EmployeeLaptop> findByLaptop = repository.findByLaptop(laptop);
        if(findByLaptop.isPresent()) throw new BadRequestException(String.format("Laptop with serial number ' %s ' already assigned", laptop.getSerialNumber()));

        EmployeeLaptop employeeLaptop = new EmployeeLaptop();
        employeeLaptop.setEmployee(employee);
        employeeLaptop.setLaptop(laptop);

        return repository.save(employeeLaptop);
    }
}
