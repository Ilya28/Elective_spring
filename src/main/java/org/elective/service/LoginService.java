package org.elective.service;

import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.repos.UserRepo;
import org.elective.service.converters.UserConverter;
import org.elective.service.preparing.LocalizationPreparingService;
import org.elective.service.preparing.NavbarOnPageService;
import org.elective.service.preparing.RoleOnPageService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class LoginService  extends AbstractService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(LocalizationPreparingService localeService, NavbarOnPageService navbarService,
                        RoleOnPageService roleService, UserRepo userRepo, UserConverter userConverter,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(localeService, navbarService, roleService);
        this.userRepo = userRepo;
        this.userConverter = userConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public boolean saveNewUser(UserDTO userDTO, String locale) {
        userDTO.setRole("user");
        userDTO.setBlocked(false);
        userDTO.setLanguage(locale);
        User user = userConverter.UserDTOToUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<User> expected = userRepo.findByEmail(user.getEmail());
        if (expected.isPresent())
            return false;
        userRepo.save(user);
        return true;
    }
}
