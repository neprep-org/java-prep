package rw.pacis.ne.equipment_distribution_system.serviceImpls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateLaptopDTO;
import rw.pacis.ne.equipment_distribution_system.exceptions.BadRequestException;
import rw.pacis.ne.equipment_distribution_system.exceptions.ResourceNotFoundException;
import rw.pacis.ne.equipment_distribution_system.models.Laptop;
import rw.pacis.ne.equipment_distribution_system.repositories.ILaptopRepository;
import rw.pacis.ne.equipment_distribution_system.services.ILaptopService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaptopServiceImpl implements ILaptopService {

    private final ILaptopRepository repository;

    public LaptopServiceImpl(ILaptopRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Laptop> getLaptopsPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Laptop findById(UUID laptopId){
        return repository.findById(laptopId).orElseThrow(()->new ResourceNotFoundException("Laptop","id",laptopId));
    }

    @Override
    public List<Laptop> getLaptops() {
        return repository.findAll();
    }

    @Override
    public Laptop addNewLaptop(CreateOrUpdateLaptopDTO dto) {

        Optional<Laptop> findBySerialNumber = repository.findBySerialNumber(dto.getSerialNumber());
        if(findBySerialNumber.isPresent()) throw new BadRequestException(String.format("Laptop with serial number ' %s ' already exists", dto.getSerialNumber()));

        Laptop laptop = new Laptop();
        laptop.setLaptopManufacturer(dto.getManufacturer());
        laptop.setModel(dto.getModel());
        laptop.setSerialNumber(dto.getSerialNumber());

        return repository.save(laptop);
    }
}
