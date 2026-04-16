package com.LeaderBoard.Real_timeLeader.service;

import com.LeaderBoard.Real_timeLeader.model.User;
import com.LeaderBoard.Real_timeLeader.repo.UserRepo;
import com.LeaderBoard.Real_timeLeader.security.JwtUtil;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepo userRepository; // Matches your UserRepo.java
    private final JwtUtil jwtUtil;

    public AuthService(UserRepo userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }

    // Add the register method if it was deleted
    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }
}