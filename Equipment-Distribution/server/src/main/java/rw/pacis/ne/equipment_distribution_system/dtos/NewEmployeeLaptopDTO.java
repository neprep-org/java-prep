package rw.pacis.ne.equipment_distribution_system.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class NewEmployeeLaptopDTO {

    @NotNull
    public UUID employeeId;

    @NotNull
    public UUID laptopId;
}
