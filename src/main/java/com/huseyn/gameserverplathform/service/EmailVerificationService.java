package com.huseyn.gameserverplathform.service;

import com.huseyn.gameserverplathform.dto.VerifyEmailCodeDTO;
import com.huseyn.gameserverplathform.entity.EmailVerification;
import com.huseyn.gameserverplathform.entity.User;
import com.huseyn.gameserverplathform.repository.EmailVerificationRepository;
import com.huseyn.gameserverplathform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final UserRepository userRepository;
    private final EmailVerificationRepository verificationRepository;
    public void verifyEmail(VerifyEmailCodeDTO request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        EmailVerification verification = verificationRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Verification code not found"));
        if(verification.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Verification code expired");
        }
        if(!verification.getCode().equals(request.getCode())){
            throw new RuntimeException("Invalid verification code");
        }
        user.setEmailVerified(true);
        userRepository.save(user);
        verificationRepository.delete(verification);
    }
}
