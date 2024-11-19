package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.model.User;
import com.example.bookingtrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    // Authicated bằng JPA thi su dung UserDetailServie

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        // DTO/CustomUserDetails
        return new CustomUserDetails(user);
    }

}
