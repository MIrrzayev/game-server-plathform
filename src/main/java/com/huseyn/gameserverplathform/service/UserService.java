package com.huseyn.gameserverplathform.service;

import com.huseyn.gameserverplathform.dto.UserRequestDTO;
import com.huseyn.gameserverplathform.dto.UserResponseDTO;
import com.huseyn.gameserverplathform.entity.EmailVerification;
import com.huseyn.gameserverplathform.entity.User;
import com.huseyn.gameserverplathform.exception.ResourceNotFoundException;
import com.huseyn.gameserverplathform.mapper.UserMapper;
import com.huseyn.gameserverplathform.repository.EmailVerificationRepository;
import com.huseyn.gameserverplathform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationRepository verificationRepository;
    private final EmailService emailService;
    public UserResponseDTO createUser(UserRequestDTO request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmailVerified(false);
        User savedUser = userRepository.save(user);
        String code = String.format("%06d", new Random().nextInt(1_000_000));
        EmailVerification verification = new EmailVerification();
        verification.setUser(savedUser);
        verification.setCode(code);
        verification.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        verificationRepository.save(verification);
        emailService.sendVerificationCode(savedUser.getEmail(), code);
        return userMapper.toResponseDTO(savedUser);
    }
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponseDTO(user);
    }
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }
    public UserResponseDTO updateUser(Long id, UserRequestDTO request){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }
    public UserResponseDTO getUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return userMapper.toResponseDTO(user);
    }
}