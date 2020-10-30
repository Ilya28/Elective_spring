package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.entity.Registration;
import org.elective.entity.User;
import org.elective.repos.RegistrationRepo;
import org.elective.repos.UserRepo;
import org.elective.service.converters.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final RegistrationRepo registrationRepo;

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
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
}
