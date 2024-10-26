package com.example.bookingtrain.service;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {

        user.setRoleId(1); // Mặc định là user thường khi tạo tài khoản
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

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

        return user != null && user.getPassword().equals(password); // Kiểm tra mật khẩu có khớp không
    }

    public boolean isEmailExisted(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
