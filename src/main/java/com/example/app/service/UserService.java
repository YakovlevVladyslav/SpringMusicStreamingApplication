package com.example.app.service;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --- USER BUSINESS LOGIC ---

    /**
     * Create a new user
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get all users for the prototype list
     */
    public List<User> getAllArtists() {
        return userRepository.findAll();
    }

    /**
     * Get a single user or throw an error if the ID is wrong
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Update user: Finds existing record, maps new values, and saves.
     * Currently only updates the user name.
     */
    public User updateUserName(Long id, String newName) {
        User existingUser = getUserById(id); // Uses the method above
        existingUser.setUserName(newName);
        return userRepository.save(existingUser);
    }

    /**
     * Delete an user from the database
     */
    public void deleteUser(Long id) {
        User artist = getUserById(id);
        userRepository.delete(user);
    }

}
