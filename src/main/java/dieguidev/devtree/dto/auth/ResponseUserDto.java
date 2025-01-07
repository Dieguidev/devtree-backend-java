package dieguidev.devtree.dto.auth;

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
}
