package com.example.loginservice.controller;

import com.example.loginservice.model.User;
import com.example.loginservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User request) {
        userService.registerUser(request.getFullname(), request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User request) {
        String token = userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody User request) {
        userService.logoutUser(request.getId());
        return ResponseEntity.ok("User logged out successfully");
    }
}
