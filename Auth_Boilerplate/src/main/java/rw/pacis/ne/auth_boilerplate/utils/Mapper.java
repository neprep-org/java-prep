package rw.pacis.ne.auth_boilerplate.utils;

import org.modelmapper.ModelMapper;
import rw.pacis.ne.auth_boilerplate.models.User;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();

    public static User getUserFromDTO(Object object) {
        return modelMapper.map(object, User.class);
    }


}
