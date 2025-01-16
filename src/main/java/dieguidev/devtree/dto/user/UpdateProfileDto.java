package dieguidev.devtree.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateProfileDto implements Serializable {
    private String handle;
    private String description;
}
