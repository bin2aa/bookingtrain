package com.example.bookingtrain.service;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.model.PasswordResetToken;
import com.example.bookingtrain.repository.UserRepository;
import com.example.bookingtrain.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Calendar;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    // CRUD Operations
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Page<User> searchUsersByUsername(String username, Pageable pageable) {
        return userRepository.findByUsernameContaining(username, pageable);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoleId() == null) {
            user.setRoleId(1); // Default role
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            if (!user.getPassword().equals(existingUser.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Authentication Methods
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        User user = findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public boolean isEmailExisted(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // Password Reset Methods
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        tokenRepository.save(myToken);
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = tokenRepository.findByToken(token);
        if (passToken == null) {
            return "invalidToken";
        }

        Calendar cal = Calendar.getInstance();
        if (passToken.isExpired()) {
            tokenRepository.delete(passToken);
            return "expired";
        }

        return null;
    }

    public User getUserByPasswordResetToken(String token) {
        return tokenRepository.findByToken(token).getUser();
    }

    public void changeUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // Profile Update Methods
    public User updateUserProfile(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public boolean updatePassword(Integer userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Page<User> searchUsersByRoleName(String roleName, Pageable pageable) {
        return userRepository.findByRoleNameContaining(roleName, pageable);
    }
}

// package com.example.bookingtrain.service;

// import com.example.bookingtrain.model.User;
// import com.example.bookingtrain.repository.UserRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import java.util.List;

// @Service
// public class UserService {

// @Autowired
// private UserRepository userRepository;

// @Autowired
// private PasswordEncoder passwordEncoder;

// public Page<User> getAllUsers(Pageable pageable) {
// return userRepository.findAll(pageable);
// }

// public Page<User> searchUsersByUsername(String username, Pageable pageable) {
// return userRepository.findByUsernameContaining(username, pageable);
// }

// public List<User> getAllUsers() {
// return userRepository.findAll();
// }

// public User getUserById(Integer id) {
// return userRepository.findById(id).orElse(null);
// }

// public User createUser(User user) {
// user.setPassword(passwordEncoder.encode(user.getPassword()));
// if (user.getRoleId() == null) {
// user.setRoleId(1); // Đặt mặc định chỉ khi roleId không được cung cấp
// }
// return userRepository.save(user);
// }

// public User updateUser(User user) {
// // return userRepository.saveAndFlush(user);
// User existingUser = userRepository.findById(user.getUserId()).orElse(null);
// if (existingUser != null && !passwordEncoder.matches(user.getPassword(),
// existingUser.getPassword())) {
// user.setPassword(passwordEncoder.encode(user.getPassword()));
// }
// return userRepository.saveAndFlush(user);
// }

// public void deleteUser(Integer id) {
// userRepository.deleteById(id);
// }

// public User findByUsername(String username) {
// return userRepository.findByUsername(username);
// }

// public User findByEmail(String email) {
// return userRepository.findByEmail(email);
// }

// public boolean authenticate(String email, String password) {
// User user = findByEmail(email);
// return user != null && passwordEncoder.matches(password, user.getPassword());
// // Kiểm tra mật khẩu

// }

// public boolean isEmailExisted(String email) {
// return userRepository.findByEmail(email) != null;
// }

// public User updateUserProfile(User user) {
// User existingUser = userRepository.findById(user.getUserId()).orElse(null);
// if (existingUser != null) {
// existingUser.setUsername(user.getUsername());
// return userRepository.save(existingUser);
// }
// return null;
// }

// public boolean updatePassword(Integer userId, String currentPassword, String
// newPassword) {
// User user = userRepository.findById(userId).orElse(null);
// if (user != null && passwordEncoder.matches(currentPassword,
// user.getPassword())) {
// user.setPassword(passwordEncoder.encode(newPassword));
// userRepository.save(user);
// return true;
// }
// return false;
// }

// }
