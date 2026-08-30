package com.huseyn.gameserverplathform.repository;

import com.huseyn.gameserverplathform.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByUserId(Long userId);
}
