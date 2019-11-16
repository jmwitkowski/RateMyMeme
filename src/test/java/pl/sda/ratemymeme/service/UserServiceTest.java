package pl.sda.ratemymeme.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private Role roleUser = new Role("USER");
    private User user = new User("user", "user@gmail.com", "password", LocalDate.now(), roleUser);

    private UserRepository getUserRepositoryMock() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById("user")).thenReturn(Optional.of(user));
        when(userRepository.findById("adminlogin")).thenReturn(Optional.empty());
        return userRepository;
    }

    UserService userService = new UserService(getUserRepositoryMock());

    @Test
    public void shouldAddUserToDataBase() {
        // given is upper
        // when
        User user1 = userService.addUserToDataBase(user.getLogin(), user.getEmail(), user.getPassword(), user.getRole());
        // then
        assertThat(user1).isEqualTo(user);
    }

    @Test
    public void shouldShowUserByHisLogin() {
        // when
        User userExpected = userService.findByLogin("user");
        // then
        assertThat(userExpected).isEqualTo(user);
    }


    @Test
    public void shouldNotFindAdminByLogin() {
        // when
        // then
        assertThrows(UsernameNotFoundException.class, () -> userService.findByLogin("adminlogin"));
    }

}