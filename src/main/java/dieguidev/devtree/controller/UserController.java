package dieguidev.devtree.controller;

import dieguidev.devtree.config.security.CustomerDetailsService;
import dieguidev.devtree.dto.auth.ResponseUserDto;
import dieguidev.devtree.persistence.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @GetMapping
    public ResponseEntity<ResponseUserDto> getUser() {
        User user = customerDetailsService.getUserDetail();
        ResponseUserDto responseUserDto = ResponseUserDto.fromUser(user);
        return ResponseEntity.ok(responseUserDto);
    }
}
