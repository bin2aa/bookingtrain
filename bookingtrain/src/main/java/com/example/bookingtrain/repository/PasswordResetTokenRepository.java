package com.example.bookingtrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookingtrain.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}