package pl.sda.ratemymeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.exception.EmailExistsException;
import pl.sda.ratemymeme.exception.LoginExistsException;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUserToDataBase(String login, String email, String password, Role role) throws RuntimeException {

        if (userRepository.findAll().stream()
                .anyMatch(user -> user.getLogin().equals(login)))
        {
            throw new LoginExistsException("There is an account with that login: " + login);
        }

        if (userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(email)))
        {
            throw new EmailExistsException("There is an account with that email adress: " + email);
        }

        User newUser = new User(login, email, password, LocalDate.now(), role);
        newUser.setRole(role);
        userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byId = userRepository.findById(s);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Could not find user with username " + s));
    }
}
