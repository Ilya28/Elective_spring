package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.repos.UserRepo;
import org.elective.service.converters.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;

    @Transactional
    public boolean saveUser(UserDTO userDTO) {
        userDTO.setRole("user");
        User user = userConverter.UserDTOToUser(userDTO);
        Optional<User> expected = userRepo.findByEmail(user.getEmail());
        if (expected.isPresent())
            return false;
        userRepo.save(user);
        return true;
    }
}
