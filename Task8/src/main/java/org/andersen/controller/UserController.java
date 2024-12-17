package org.andersen.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.andersen.exception.UserActivationIsDisabledException;
import org.andersen.model.user.User;
import org.andersen.service.UserService;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user")
    @ApiResponse(code = 200, message = "OK")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/new")
    @ApiOperation(value = "Create user")
    @ApiResponse(code = 201, message = "Created")
    public User createUser(@RequestBody User userDetails) {
        return userService.addUser(userDetails);
    }

    @PutMapping("/{id}/activate")
    @ApiOperation(value = "Activate user")
    @ApiResponse(code = 200, message = "OK")
    public void activateUser(@PathVariable Long id) throws UserActivationIsDisabledException {
        userService.activateUser(id);
    }

    @DeleteMapping("/{id}/delete")
    @ApiOperation(value = "Delete user")
    @ApiResponse(code = 200, message = "OK")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
