package com.cinema.ticketing.iam.controller;

import com.cinema.ticketing.iam.dto.request.LoginRequest;
import com.cinema.ticketing.iam.dto.request.RegisterRequest;
import com.cinema.ticketing.iam.dto.response.AuthResponse;
import com.cinema.ticketing.iam.entity.User;
import com.cinema.ticketing.iam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userService.login(request);

        // فعلاً fake token
        String fakeToken = "FAKE_TOKEN_" + user.getUsername();

        return ResponseEntity.ok(new AuthResponse(fakeToken));
    }
}
