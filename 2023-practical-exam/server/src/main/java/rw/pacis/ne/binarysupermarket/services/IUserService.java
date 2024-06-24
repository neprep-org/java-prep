package rw.pacis.ne.binarysupermarket.services;

import rw.pacis.ne.binarysupermarket.models.User;


public interface IUserService {

    User create(User user);

    boolean isNotUnique(User user);

    void validateNewRegistration(User user);

    User getLoggedInUser();

}