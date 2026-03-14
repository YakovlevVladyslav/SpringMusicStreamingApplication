package com.example.app.controller;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // 3. PATCH - Update only the name
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUserName(@PathVariable Long id, @RequestBody User userDetails) {
        // Extracts the userName from the incoming JSON object
        User updatedUser = userService.updateUserName(id, userDetails.getUserName());
        return ResponseEntity.ok(updatedUser);
    }

    // 4. DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // Note: Your UserService currently has a typo where 'getAllArtists' returns Users.
        // I've called that method here to match your provided Service code.
        return ResponseEntity.ok(userService.getAllArtists());
    }
}