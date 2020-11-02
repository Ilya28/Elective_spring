package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.Registration;
import org.elective.entity.User;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.UserConverter;
import org.elective.service.preparing.LocalizationPreparingService;
import org.elective.service.preparing.NavbarOnPageService;
import org.elective.service.preparing.RoleOnPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersService extends AbstractService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final RegistrationRepo registrationRepo;

    public UsersService(LocalizationPreparingService localeService, NavbarOnPageService navbarService,
                        RoleOnPageService roleService, UserRepo userRepo, UserConverter userConverter,
                        RegistrationRepo registrationRepo) {
        super(localeService, navbarService, roleService);
        this.userRepo = userRepo;
        this.userConverter = userConverter;
        this.registrationRepo = registrationRepo;
    }

    public Page<UserDTO> getUsersByCourseName(String nameEN, Pageable pageable) {
        Page<Registration> registrations = registrationRepo.findRegistrationByCourse_NameEN(nameEN, pageable);
        return registrations
                .map(Registration::getUser)
                .map(userConverter::userToUserDTO);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> page = userRepo.findAll(pageable);
        return page.map(userConverter::userToUserDTO);
    }
}
