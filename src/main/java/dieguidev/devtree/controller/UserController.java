package dieguidev.devtree.controller;

import dieguidev.devtree.config.security.CustomerDetailsService;
import dieguidev.devtree.dto.auth.ResponseUserDto;
import dieguidev.devtree.dto.user.UpdateProfileDto;
import dieguidev.devtree.persistence.entity.security.User;
import dieguidev.devtree.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseUserDto> getUser() {
        User user = customerDetailsService.getUserDetail();
        ResponseUserDto responseUserDto = ResponseUserDto.fromUser(user);
        return ResponseEntity.ok(responseUserDto);
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody @Valid UpdateProfileDto updateProfileDto) {

        return ResponseEntity.ok(userService.updateProfile(updateProfileDto, customerDetailsService.getUserDetail()));
    }
}
