package org.example.stayeasy.controller;

import org.example.stayeasy.model.User;
import org.example.stayeasy.repository.UserRepository;
import org.example.stayeasy.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping("user/")
    public void post(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("user/{id}")
    public void updateUser(@RequestBody User user) {
         userService.save(user);
    }

    @DeleteMapping("user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
