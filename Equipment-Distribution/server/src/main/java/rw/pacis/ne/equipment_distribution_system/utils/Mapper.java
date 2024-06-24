package rw.pacis.ne.equipment_distribution_system.utils;

import org.modelmapper.ModelMapper;
import rw.pacis.ne.equipment_distribution_system.models.User;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();

    public static User getUserFromDTO(Object object) {
        return modelMapper.map(object, User.class);
    }


}
