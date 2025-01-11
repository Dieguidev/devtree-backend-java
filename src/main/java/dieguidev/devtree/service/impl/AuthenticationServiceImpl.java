package dieguidev.devtree.service.impl;

import dieguidev.devtree.config.security.CustomerDetailsService;
import dieguidev.devtree.config.security.jwt.JwtUtil;
import dieguidev.devtree.dto.auth.LoginUserDto;
import dieguidev.devtree.dto.auth.RegisterUserDto;
import dieguidev.devtree.dto.auth.ResponseLoginDto;
import dieguidev.devtree.dto.auth.ResponseUserDto;
import dieguidev.devtree.exception.CustomException;
import dieguidev.devtree.persistence.entity.security.User;
import dieguidev.devtree.persistence.repository.UserRepository;
import dieguidev.devtree.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utils.Slugify;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserDto registerUserDto) {
        if (!registerUserDto.getPassword().equals(registerUserDto.getPasswordConfirmation())) {
            throw new CustomException("Passwords do not match");
        }
        if (userRepository.findByEmail(registerUserDto.getEmail()).isPresent()) {
            throw new CustomException("Email already in use");
        }
        if (userRepository.findByHandle(Slugify.slugify(registerUserDto.getHandle())).isPresent()) {
            throw new CustomException("Handle already in use");
        }

        User user = new User();
        user.setName(registerUserDto.getName());
        user.setEmail(registerUserDto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setHandle(Slugify.slugify(registerUserDto.getHandle()));

        return userRepository.save(user);
    }

    @Override
    public ResponseLoginDto login(LoginUserDto loginUserDto) {
//        User user = userRepository.findByEmail(loginUserDto.getEmail())
//                .orElseThrow(() -> new CustomException("Invalid email or password"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(loginUserDto.getEmail())
                    .orElseThrow(() -> new CustomException("Invalid email or password"));
            if (user.getIsActive() == true) {
                String token = jwtUtil.generateToken(user.getEmail());
                return new ResponseLoginDto(ResponseUserDto.fromUser(user), token);
            } else {
                throw new CustomException("User is not active");
            }
        }
        throw new CustomException("Invalid email or password");
    }
}
