package dieguidev.devtree.service;

import dieguidev.devtree.dto.auth.RegisterUserDto;
import dieguidev.devtree.persistence.entity.security.User;

public interface AuthenticationService {
    User registerUser(RegisterUserDto registerUserDto);

}
