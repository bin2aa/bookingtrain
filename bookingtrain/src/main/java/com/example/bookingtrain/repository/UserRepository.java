package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUserId(Integer userId);

    User findByEmail(String email);
}
