package org.elective.service.converters;

import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public User UserDTOToUser(UserDTO userDTO) {
        Role role;
        switch (userDTO.getRole().toLowerCase()) {
            case "admin":
                role = Role.ADMIN;
                break;
            case "teacher":
                role = Role.TEACHER;
                break;
            case "user":
                role = Role.USER;
                break;
            default:
                throw new IllegalStateException("Unknown role: " + userDTO.getRole());
        }
        return User.builder()
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
                .blocked(user.isBlocked())
                .email(user.getEmail())
                .language(user.getLanguage())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();
    }
}
