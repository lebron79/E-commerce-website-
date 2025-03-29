    package com.example.project.services;
    import com.example.project.dto.AuthResponse;
    import com.example.project.exceptions.UserNotFoundException;
    import com.example.project.models.User;
    import com.example.project.repositories.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public User registerUser(User user) {
            // Only validate email uniqueness (username duplicates allowed)
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }

            // Encode password and save user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        public AuthResponse authenticateUser(String email, String password) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("Invalid password");
            }

            return convertToAuthResponse(user);
        }

        private AuthResponse convertToAuthResponse(User user) {
            AuthResponse response = new AuthResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            return response;
        }


        // Returns first user found with the username (since duplicates allowed)
        public User findByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User with username '" + username + "' not found"));
        }

        // New method to find ALL users with same username
        public List<User> findAllUsersByUsername(String username) {
            return userRepository.findByUsernameContaining(username);
        }

        public User findByEmail(String email) {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User with email '" + email + "' not found"));
        }

        public boolean usernameExists(String username) {
            return userRepository.existsByUsername(username);
        }

        public boolean emailExists(String email) {
            return userRepository.existsByEmail(email);
        }

        public User findById(Long id) {
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        }

        public List<User> findAllUsers() {
            return userRepository.findAll();
        }

        public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new UserNotFoundException("User with ID " + id + " not found");
            }
            userRepository.deleteById(id);
        }
    }