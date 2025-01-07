package dieguidev.devtree.service.impl;

import dieguidev.devtree.dto.auth.RegisterUserDto;
import dieguidev.devtree.exception.CustomException;
import dieguidev.devtree.persistence.entity.security.User;
import dieguidev.devtree.persistence.repository.UserRepository;
import dieguidev.devtree.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Slugify;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(RegisterUserDto registerUserDto) {
        if (!registerUserDto.getPassword().equals(registerUserDto.getPasswordConfirmation())) {
            throw new CustomException("Passwords do not match");
        }

        User user = new User();
        user.setName(registerUserDto.getName());
        user.setEmail(registerUserDto.getEmail().toLowerCase());
        user.setPassword(registerUserDto.getPassword());
        user.setHandle(Slugify.slugify(registerUserDto.getHandle()));

        return userRepository.save(user);
    }
}
