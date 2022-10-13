package com.indocyber.usermgmt;

import com.indocyber.usermgmt.entity.MstUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationUserDetails implements UserDetails {
    private String username;

    private String password;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public ApplicationUserDetails(MstUser mstUser) {
        this.username = mstUser.getId();
        this.password = mstUser.getPassword();
        this.authorities = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
