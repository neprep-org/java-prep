package rw.pacis.ne.equipment_distribution_system.services;

import rw.pacis.ne.equipment_distribution_system.models.User;


public interface IUserService {

    User create(User user);

    boolean isNotUnique(User user);

    void validateNewRegistration(User user);

    User getLoggedInUser();

}