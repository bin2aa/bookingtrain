package com.example.bookingtrain.DTO;

import java.util.Arrays;
import java.util.Collection;

import com.example.bookingtrain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public User getUser() {
        return user;
    }

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        switch (this.user.getRole().getRoleId()) {
            case 1:
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            // case 2:
            // switch(this.user.get){
            //
            // }
            default:
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return user.getUsername();
    }

    public int getUserId() {
        return user.getUserId();
    }

    public String getRole() {
        return user.getRole().getRoleName();
    }

}