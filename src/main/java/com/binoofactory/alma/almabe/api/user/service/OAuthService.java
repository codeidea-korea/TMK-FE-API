package com.binoofactory.alma.almabe.api.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OAuthService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username);
}
