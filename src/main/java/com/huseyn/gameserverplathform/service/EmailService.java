package com.huseyn.gameserverplathform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    public void sendVerificationCode(String email, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("RedWolf Game Verification");
        message.setText("Your RedWolf verification code is: " + code + "\n\nThis code will expire in 5 minutes");
        System.out.println("MAIL USERNAME = " + System.getenv("MAIL_USERNAME"));
        mailSender.send(message);
    }
}
