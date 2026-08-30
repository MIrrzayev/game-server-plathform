package com.huseyn.gameserverplathform.controller;

import com.huseyn.gameserverplathform.dto.*;
import com.huseyn.gameserverplathform.entity.EmailVerification;
import com.huseyn.gameserverplathform.service.AuthService;
import com.huseyn.gameserverplathform.service.EmailVerificationService;
import com.huseyn.gameserverplathform.service.UserService;
import jakarta.validation.Valid;
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
    private final AuthService authService;
    private final UserService userService;
    private final EmailVerificationService emailVerificationService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request){
        return ResponseEntity.ok(userService.createUser(request));
    }
    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@Valid @RequestBody VerifyEmailCodeDTO request){
        emailVerificationService.verifyEmail(request);
        return ResponseEntity.ok("Email verified successfully");
    }
}
