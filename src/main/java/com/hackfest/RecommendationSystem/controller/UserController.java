package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.LoginResponse;
import com.hackfest.RecommendationSystem.dto.UserDTO;
import com.hackfest.RecommendationSystem.entity.User;
import com.hackfest.RecommendationSystem.exception.BadRequestException;
import com.hackfest.RecommendationSystem.service.AuthenticationService;
import com.hackfest.RecommendationSystem.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.hackfest.RecommendationSystem.constants.RecommendationConstants.USER_MAP;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        USER_MAP.put(user.getUsername(), user);
        return "User Saved Successfully";
    }

    @GetMapping("/getUsers")
    public List<User> getAllUsers() {
        if (USER_MAP.isEmpty()) {
            throw new BadRequestException("User hi nahi hai bhai");
        }
        return new ArrayList<>(USER_MAP.values());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(UserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
