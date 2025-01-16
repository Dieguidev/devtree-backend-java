package dieguidev.devtree.service;

import dieguidev.devtree.dto.user.UpdateProfileDto;
import dieguidev.devtree.persistence.entity.security.User;

public interface UserService {
    String updateProfile(UpdateProfileDto updateProfileDto, User user);
}
