package com.hcd.websketch.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Service
public class FacebookSignInAdapter implements SignInAdapter {

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(connection.getDisplayName(),
                null, List.of(new SimpleGrantedAuthority("FACEBOOK_USER")));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        return null;
    }
}
