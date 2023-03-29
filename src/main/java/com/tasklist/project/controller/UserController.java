package com.tasklist.project.controller;

import com.tasklist.project.dto.UserDto;
import com.tasklist.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email.toLowerCase().trim());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register")
    public void registerUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password) {
        userService.registerUser(username, email, password);
    }
}
