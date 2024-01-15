package com.socialmedia.coreapi.service

import org.springframework.security.core.Authentication;

interface AuthenticationService {
    Authentication getAuthentication();

    String getPrincipalSub();
}