package dieguidev.devtree.service;

import dieguidev.devtree.dto.auth.LoginUserDto;
import dieguidev.devtree.dto.auth.RegisterUserDto;
import dieguidev.devtree.dto.auth.ResponseLoginDto;
import dieguidev.devtree.dto.auth.ResponseUserDto;
import dieguidev.devtree.persistence.entity.security.User;

public interface AuthenticationService {
    User registerUser(RegisterUserDto registerUserDto);
    ResponseLoginDto login(LoginUserDto loginUserDto);

}
