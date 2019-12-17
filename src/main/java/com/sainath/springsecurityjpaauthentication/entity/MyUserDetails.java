package com.sainath.springsecurityjpaauthentication.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Character active;
    private String roles;
    private List<GrantedAuthority> grantedAuthorities;

    public MyUserDetails() {
    }

    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.getActive();
        // grantedAuthorities = new ArrayList<>();
        // Arrays.asList(user.getRoles().split(",")).forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
        // Alternative way using stream
        grantedAuthorities = Arrays.stream(user.getRoles().split(","))
                                    // .map(role -> new SimpleGrantedAuthority(role))
                                    // above can be replaced using method reference
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active.compareTo('Y') == 0;
    }
}
