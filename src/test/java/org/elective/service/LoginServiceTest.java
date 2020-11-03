package org.elective.service;

import org.elective.dto.UserDTO;
import org.elective.entity.User;
import org.elective.repos.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginServiceTest {
    @Autowired LoginService loginService;
    @MockBean private UserRepo userRepo;

    @Test
    void saveNewUser() {
        UserDTO userDTO = UserDTO.builder()
                .email("email")
                .role("ROLE_USER")
                .password("pass")
                .build();
        Mockito.doReturn(Optional.empty()).when(userRepo).findByEmail("email");
        Assert.assertTrue(loginService.saveNewUser(userDTO, "en"));
        Mockito.verify(userRepo, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }
}