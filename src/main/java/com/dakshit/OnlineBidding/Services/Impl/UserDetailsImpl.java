package com.dakshit.OnlineBidding.Services.Impl;

import com.dakshit.OnlineBidding.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private long id;
    private String username;
    private String email;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities = null;

    public UserDetailsImpl(Long id, String username, String email, String password) {
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static UserDetailsImpl build(User user) {
        System.out.println("do UserDetailsImpl build！");
        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getEmailId(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
