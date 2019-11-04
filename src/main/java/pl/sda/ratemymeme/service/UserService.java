package pl.sda.ratemymeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUserToDataBase(String login, String email, String password) {
        User newUser = new User(login,email,password, LocalDate.now());
        userRepository.save(newUser);
    }


}
