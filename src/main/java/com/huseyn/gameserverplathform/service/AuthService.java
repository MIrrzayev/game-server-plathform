package com.huseyn.gameserverplathform.service;

import com.huseyn.gameserverplathform.dto.LoginRequestDTO;
import com.huseyn.gameserverplathform.dto.LoginResponseDTO;
import com.huseyn.gameserverplathform.entity.User;
import com.huseyn.gameserverplathform.repository.UserRepository;
import com.huseyn.gameserverplathform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public LoginResponseDTO login(LoginRequestDTO request){
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponseDTO(token);
    }
}
