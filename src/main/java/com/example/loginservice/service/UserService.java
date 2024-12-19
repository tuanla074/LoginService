package com.example.loginservice.service;

import com.example.loginservice.model.User;
import com.example.loginservice.repository.UserRepository;
import com.example.loginservice.util.JwtUtil;
import com.example.loginservice.util.PasswordUtils;
import com.example.loginservice.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();

    public void registerUser(String fullname, String username, String rawPassword) {
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(rawPassword, salt);

        User user = new User();
        user.setId(idGenerator.nextId());
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setPasswordSalt(salt);
        user.setIsLoggedIn(false);

        userRepository.save(user);
    }

    public String loginUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String hashedPassword = PasswordUtils.hashPassword(rawPassword, user.getPasswordSalt());

        if (user.getPassword().equals(hashedPassword)) {
            user.setIsLoggedIn(true);
            userRepository.save(user);
            return jwtUtil.generateToken(user.getId(), username);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public void logoutUser(long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsLoggedIn(false);
        userRepository.save(user);
    }

}
