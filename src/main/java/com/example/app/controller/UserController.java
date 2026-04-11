package com.example.app.controller;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. CREATE -> 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 2. GET BY ID -> 200 OK
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 3. PATCH -> 200 OK
    @PatchMapping("/{id}")
    public User updateUserName(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUserName(id, userDetails.getUserName());
    }

    // 4. DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET ALL -> 200 OK
    @GetMapping
    public List<User> getAllUsers() {
        // Вызываем метод сервиса. Если в сервисе опечатка (getAllArtists),
        // советую переименовать его там для чистоты кода.
        return userService.getAllUsers();
    }
}