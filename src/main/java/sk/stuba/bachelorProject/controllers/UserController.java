package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    private User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/getUserByUserName/{username}")
    public User getUserByUsername(@PathVariable(name = "username") String userName) {
        return userService.getUserByUsername(userName);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser/{userName}")
    public User updateUser(@PathVariable(name = "userName") String userName, @RequestBody User user) {
        return userService.updateUser(userName, user);
    }

    @DeleteMapping("/deleteUser/{userName}")
    public void deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }
}
