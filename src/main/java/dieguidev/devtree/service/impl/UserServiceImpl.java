package dieguidev.devtree.service.impl;

import dieguidev.devtree.dto.user.UpdateProfileDto;
import dieguidev.devtree.persistence.entity.security.User;
import dieguidev.devtree.persistence.repository.UserRepository;
import dieguidev.devtree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Slugify;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String updateProfile(UpdateProfileDto updateProfileDto, User user) {
        String handle = updateProfileDto.getHandle() != null ? Slugify.slugify(updateProfileDto.getHandle()) : null;
        String description = updateProfileDto.getDescription();

        if (Objects.equals(updateProfileDto.getHandle(), user.getHandle()) && handle != null && userRepository.findByHandle(Slugify.slugify(updateProfileDto.getHandle())).isPresent()) {
            return "El handle ya está en uso";
        }
        int rowsUpdated = userRepository.updateHandleAndDescription(
                user.getId(),
                handle,
                description
        );
        return rowsUpdated > 0 ? "Perfil actualizado exitosamente" : "Error al actualizar el perfil";
    }
}
