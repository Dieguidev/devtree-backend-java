package dieguidev.devtree.dto.auth;

import dieguidev.devtree.persistence.entity.security.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserDto {
    private String id;
    private String handle;
    private String name;
    private String email;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static ResponseUserDto fromUser(User user) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setHandle(user.getHandle());
        responseUserDto.setName(user.getName());
        responseUserDto.setEmail(user.getEmail());
        responseUserDto.setIsActive(user.getIsActive());
        responseUserDto.setCreatedAt(user.getCreatedAt());
        return responseUserDto;
    }
}
