package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("registry");
    }

    @PostMapping(value = "/addnewuser", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> addUser(@RequestPart("login") String login, @RequestPart("email") String email, @RequestPart("password") String password) {
        userService.addUserToDataBase(login,email,password);
        return ResponseEntity.ok("User added!");
    }

}
