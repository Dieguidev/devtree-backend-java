package dieguidev.devtree.controller;

import dieguidev.devtree.dto.auth.RegisterUserDto;
import dieguidev.devtree.dto.auth.ResponseUserDto;
import dieguidev.devtree.persistence.entity.security.User;
import dieguidev.devtree.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> registerUser (@RequestBody @Valid RegisterUserDto registerUserDto){
        User user = authenticationService.registerUser(registerUserDto);
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setHandle(user.getHandle());
        responseUserDto.setName(user.getName());
        responseUserDto.setEmail(user.getEmail());
        responseUserDto.setIsActive(user.getIsActive());
        responseUserDto.setCreatedAt(user.getCreatedAt());
        return ResponseEntity.ok(responseUserDto);
    }
}
