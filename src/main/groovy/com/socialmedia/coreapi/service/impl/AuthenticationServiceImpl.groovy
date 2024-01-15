package com.socialmedia.coreapi.service.impl

import com.socialmedia.coreapi.service.AuthenticationService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service;

@Service
class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    String getPrincipalSub() {
        SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .getAt("claims")
                .getAt("sub")
    }


}