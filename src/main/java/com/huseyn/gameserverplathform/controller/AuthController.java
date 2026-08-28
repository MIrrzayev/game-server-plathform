package com.huseyn.gameserverplathform.controller;

import com.huseyn.gameserverplathform.dto.LoginRequestDTO;
import com.huseyn.gameserverplathform.dto.LoginResponseDTO;
import com.huseyn.gameserverplathform.dto.UserRequestDTO;
import com.huseyn.gameserverplathform.dto.UserResponseDTO;
import com.huseyn.gameserverplathform.service.AuthService;
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
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request){
        return ResponseEntity.ok(userService.createUser(request));
    }
}
