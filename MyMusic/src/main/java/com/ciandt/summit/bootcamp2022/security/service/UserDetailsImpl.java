package com.ciandt.summit.bootcamp2022.security.service;

import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final String id;

    private final String userName;

    private final String password;
    private final Playlist playlist;
    private final UserType userType;

    public UserDetailsImpl(String id, String userName, String password, Playlist playlist,
                           UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.playlist = playlist;
        this.userType = userType;
    }

    public static UserDetailsImpl build(User user, String password) {

        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                password, user.getPlaylist(),
                user.getUserType());
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
