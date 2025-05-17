package com.Booking.demo.API.Security;

import com.Booking.demo.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final User user;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.user = user;

        if (user.getRole() != null) {
            this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        } else {
            this.authorities = Collections.emptyList();
        }
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
