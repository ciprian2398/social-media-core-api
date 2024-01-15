package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.model.User
import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono;

interface AuthenticationService {
    Authentication getAuthentication();

    String getPrincipalSub();

    Mono<User> getPrincipalUser()
}