package com.hackfest.RecommendationSystem.entity;

import com.hackfest.RecommendationSystem.dto.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
public class User implements UserDetails {

    String username;
    String name;
    String password;

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
