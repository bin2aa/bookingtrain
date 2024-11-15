package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUserId(Integer userId);

    User findByEmail(String email);

    // @Query("SELECT u FROM User u ORDER BY u.userId")
    // Page<User> findAllOrderByUserId(Pageable pageable);

    Page<User> findAll(Pageable pageable);
}
