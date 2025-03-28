package com.example.project.services;
import java.util.List;
import com.example.project.dto.user.AuthResponse;
import com.example.project.dto.user.LoginRequest;
import com.example.project.dto.user.UserUpdateRequest;
import com.example.project.exceptions.UserNotFoundException;
import com.example.project.models.users.User;
import com.example.project.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    public List<User> getAllUsers() {
        try {
            logger.info("Fetching all users");
            return userRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all users", e);
            throw e;
        }
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        try {
            // Log incoming user details
            logger.info("Registering user - Username: {}, Email: {}", user.getUsername(), user.getEmail());

            // Check email uniqueness
            if (userRepository.existsByEmail(user.getEmail())) {
                logger.warn("Email already exists: {}", user.getEmail());
                throw new IllegalArgumentException("Email already exists");
            }

            // Encode password
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // Save user
            User savedUser = userRepository.save(user);

            logger.info("User saved successfully - ID: {}, Username: {}", savedUser.getId(), savedUser.getUsername());

            return savedUser;
        } catch (Exception e) {
            logger.error("Error registering user", e);
            throw e;
        }
    }

    public AuthResponse loginUser(LoginRequest loginRequest) {
        try {
            // Find user by email
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + loginRequest.getEmail()));

            // Check password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Invalid credentials");
            }

            // Convert to AuthResponse
            return convertToAuthResponse(user);
        } catch (Exception e) {
            logger.error("Login error", e);
            throw e;
        }
    }

    public AuthResponse convertToAuthResponse(User user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }
    @Transactional
    public void deleteUser(Long id) {
        // Check if user exists
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        // Delete the user
        userRepository.delete(user);

        logger.info("User deleted successfully - ID: {}", id);
    }
    @Transactional
    public User updateUser(Long id, UserUpdateRequest updateRequest) {
        try {
            // Find the user
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

            // Check if email is being changed and is unique
            if (updateRequest.getEmail() != null &&
                    !existingUser.getEmail().equals(updateRequest.getEmail())) {
                if (userRepository.existsByEmail(updateRequest.getEmail())) {
                    logger.warn("Email already exists: {}", updateRequest.getEmail());
                    throw new IllegalArgumentException("Email already exists");
                }
                existingUser.setEmail(updateRequest.getEmail());
            }

            // Update username if provided
            if (updateRequest.getUsername() != null) {
                existingUser.setUsername(updateRequest.getUsername());
            }

            // Update password if provided
            if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(updateRequest.getPassword());
                existingUser.setPassword(encodedPassword);
            }

            // Save and return updated user
            User updatedUser = userRepository.save(existingUser);

            logger.info("User updated successfully - ID: {}, Username: {}", updatedUser.getId(), updatedUser.getUsername());

            return updatedUser;
        } catch (Exception e) {
            logger.error("Error updating user", e);
            throw e;
        }
    }

}