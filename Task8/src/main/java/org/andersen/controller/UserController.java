package org.andersen.controller;

import lombok.RequiredArgsConstructor;
import org.andersen.exception.UserActivationIsDisabledException;
import org.andersen.model.user.User;
import org.andersen.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/new")
    public User createUser(@RequestBody User userDetails) {
        return userService.addUser(userDetails);
    }

    @PutMapping("/{id}/activate")
    public void activateUser(@PathVariable Long id) throws UserActivationIsDisabledException {
        userService.activateUser(id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
