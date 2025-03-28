package com.example.project.controllers;

import com.example.project.dto.user.AuthResponse;
import com.example.project.dto.user.LoginRequest;
import com.example.project.dto.user.RegistrationRequest;
import com.example.project.dto.user.UserUpdateRequest;
import com.example.project.models.users.User;
import com.example.project.models.users.CustomerUser;
import com.example.project.models.users.DeveloperUser;
import com.example.project.models.users.AdminUser;
import com.example.project.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/{userType}")
    public ResponseEntity<?> registerUser(
            @PathVariable String userType,
            @RequestBody RegistrationRequest registrationRequest
    ) {
        try {
            logger.info("Received registration request. User Type: {}", userType);
            logger.info("Registration Request: {}", registrationRequest);

            User registeredUser = switch (userType.toUpperCase()) {
                case "CUSTOMER" -> {
                    CustomerUser customerUser = new CustomerUser();
                    customerUser.setUsername(registrationRequest.getUsername());
                    customerUser.setEmail(registrationRequest.getEmail());
                    customerUser.setPassword(registrationRequest.getPassword());
                    logger.info("Creating Customer User");
                    yield userService.registerUser(customerUser);
                }
                case "DEVELOPER" -> {
                    DeveloperUser developerUser = new DeveloperUser();
                    developerUser.setUsername(registrationRequest.getUsername());
                    developerUser.setEmail(registrationRequest.getEmail());
                    developerUser.setPassword(registrationRequest.getPassword());
                    developerUser.setSpecialization("Unspecified");
                    logger.info("Creating Developer User");
                    yield userService.registerUser(developerUser);
                }
                case "ADMIN" -> {
                    AdminUser adminUser = new AdminUser();
                    adminUser.setUsername(registrationRequest.getUsername());
                    adminUser.setEmail(registrationRequest.getEmail());
                    adminUser.setPassword(registrationRequest.getPassword());
                    adminUser.setAdminLevel("Standard");
                    logger.info("Creating Admin User");
                    yield userService.registerUser(adminUser);
                }
                default -> throw new IllegalArgumentException("Invalid user type: " + userType);
            };

            logger.info("User registered successfully: {}", registeredUser);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            logger.error("Registration error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            logger.info("Received request to get all users");
            List<User> users = userService.getAllUsers();
            logger.info("Returning {} users", users.size());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error getting all users", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Received login request for email: {}", loginRequest.getEmail());

            AuthResponse authResponse = userService.loginUser(loginRequest);

            logger.info("User logged in successfully: {}", authResponse.getUsername());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.error("Login error", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            logger.info("Received delete request for user ID: {}", id);

            userService.deleteUser(id);

            logger.info("User deleted successfully: {}", id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            logger.error("Delete user error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest updateRequest
    ) {
        try {
            logger.info("Received update request for user ID: {}", id);

            User updatedUser = userService.updateUser(id, updateRequest);

            logger.info("User updated successfully: {}", updatedUser.getUsername());
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Update user error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}