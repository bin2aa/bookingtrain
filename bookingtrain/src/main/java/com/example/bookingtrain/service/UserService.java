package com.example.bookingtrain.service;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> searchUsersByUsername(String username, Pageable pageable) {
        return userRepository.findByUsernameContaining(username, pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoleId() == null) {
            user.setRoleId(1); // Đặt mặc định chỉ khi roleId không được cung cấp
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        // return userRepository.saveAndFlush(user);
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null && !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.saveAndFlush(user);
    }

    // ***** ConKienHuy
//    public User updateUser(Integer userId, User updatedUser) {
//        int checkSuccess = 0;
//        User existingUser = getUserById(userId);
//        if(existingUser != null) {
//            existingUser.setUsername(updatedUser.getUsername());
//            existingUser.setEmail(updatedUser.getEmail());
//            existingUser.setRoleId(updatedUser.getRoleId());
//
//            if(!passwordEncoder.matches(updatedUser.getPassword(), existingUser.getPassword()) || !updatedUser.getPassword().equals(existingUser.getPassword())) {
//                String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
//                existingUser.setPassword(hashedPassword);
//                checkSuccess = userRepository.updateUserWithNewHashedPass(updatedUser);
//            }
//             else checkSuccess = userRepository.updateUserWithOldHashedPass(updatedUser);
//        }
//
//        if(checkSuccess != 0)
//            return existingUser;
//        else return null;
//    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        User user = findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword()); // Kiểm tra mật khẩu

    }

    public boolean isEmailExisted(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
