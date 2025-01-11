package dieguidev.devtree.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDto {
    private ResponseUserDto user;
    private String token;
}
