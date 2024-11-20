package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUserId(Integer userId);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u " +
            "SET u.username = :#{#user.username}, u.email = :#{#user.email}, u.roleId = :#{#user.roleId}, u.password = :#{#user.password} " +
            "WHERE u.userId = :#{#user.userId}")
    int updateUserWithNewHashedPass(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("UPDATE User u " +
            "SET u.username = :#{#user.username}, u.email = :#{#user.email}, u.roleId = :#{#user.roleId} " +
            "WHERE u.userId = :#{#user.userId}")
    int updateUserWithOldHashedPass(@Param("user") User user);

    // @Query("SELECT u FROM User u ORDER BY u.userId")
    // Page<User> findAllOrderByUserId(Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Page<User> findByUsernameContaining(String username, Pageable pageable);
}
