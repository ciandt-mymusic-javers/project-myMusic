package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private static User user;

    @BeforeAll
    public static void init() {
        UserType userType = new UserType("2", "Premium");
        user = new User("1", "Maria", userType);
    }

    @Test
    void findUserWhenUserIdIsInvalidShouldReturnExceptionUserNotFound(){
        when(userRepository.findById("")).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userService.findUser("")
        );

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void findUserSuccessShouldReturnUser() {
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(user));

        assertDoesNotThrow(() -> userService.findUser("1"));
    }

}
