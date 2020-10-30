package org.elective.service.converters;

import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.entity.Role;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserConverter {
    public User UserDTOToUser(UserDTO userDTO) {
        Role role;
        try {
            role = Role.valueOf(userDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Unknown role: {}. The role param of User is set to: ROLE_USER.", userDTO.getRole());
            role = Role.ROLE_USER;
        }
        return User.builder()
                .id(userDTO.getId())
                .blocked(userDTO.isBlocked())
                .email(userDTO.getEmail())
                .language(userDTO.getLanguage())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .role(role)
                .build();
    }

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .blocked(user.isBlocked())
                .email(user.getEmail())
                .language(user.getLanguage())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();
    }
}
