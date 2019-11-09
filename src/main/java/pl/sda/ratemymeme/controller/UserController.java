package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.service.RoleService;
import pl.sda.ratemymeme.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("registry");
    }

    @PostMapping(value = "/addnewuser", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> addUser(@RequestPart("login") String login, @RequestPart("email") String email, @RequestPart("password") String password) {
        String hashedPassword = passwordEncoder.encode(password);
        userService.addUserToDataBase(login,email,hashedPassword, roleService.getRoleById(1));

        return ResponseEntity.ok("User added!");
    }

}
