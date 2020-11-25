package sk.stuba.bachelorProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    private User createUser(@RequestBody  User user){
        return userService.createUser(user);
    }
}
