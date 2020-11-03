package org.elective.service;

import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.UserConverter;
import org.elective.service.preparing.LocalizationPreparingService;
import org.elective.service.preparing.NavbarOnPageService;
import org.elective.service.preparing.RoleOnPageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UserService extends AbstractService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final RegistrationRepo registrationRepo;

    public UserService(LocalizationPreparingService localeService, NavbarOnPageService navbarService,
                       RoleOnPageService roleService, UserRepo userRepo, UserConverter userConverter,
                       RegistrationRepo registrationRepo) {
        super(localeService, navbarService, roleService);
        this.userRepo = userRepo;
        this.userConverter = userConverter;
        this.registrationRepo = registrationRepo;
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public Optional<UserDTO> getByEmail(String email){
        return userRepo.findByEmail(email).map(userConverter::userToUserDTO);
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(userConverter::userToUserDTO);
    }

    /**
     * The method is used to save changes to user parameters.
     * First you need to make sure that an object with such an ID exists.
     * Further, the DTO object is transformed into an entity.
     * After that, the object (entity) can be saved to the repository.
     * @param userDTO - This is a DTO object whose data should be used to update a record in a table.
     */
    @Transactional
    public void modifyUser(UserDTO userDTO) {
        Optional<User> user = userRepo.findById(userDTO.getId());
        if (!user.isPresent())
            return;
        User modifiedUser = userConverter.UserDTOToUser(userDTO);
        modifiedUser.setPassword(user.get().getPassword());
        userRepo.save(modifiedUser);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    public void  deleteRegistrationsForUserById(Long id) {
        registrationRepo.deleteRegistrationsByUser_Id(id);
    }
}
