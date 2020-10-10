package org.elective.service;

import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.dto.UsersDTO;
import org.elective.entity.User;
import org.elective.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    public static final int USERS_PER_PAGE = 16;

    private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepository.findAll());
    }

    public Optional<User> findByUserLogin (UserDTO userDTO){
        //TODO check for user availability. password check
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public void saveNewUser (User user){
        //TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            userRepository.save(user);
        } catch (Exception ex){
            log.info("{Почтовый адрес уже существует}");
        }

    }

    public int getCalculatePageCount(int usersCount) {
        return usersCount % USERS_PER_PAGE == 0? usersCount / USERS_PER_PAGE: usersCount / USERS_PER_PAGE + 1;
    }


}
