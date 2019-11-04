package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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
