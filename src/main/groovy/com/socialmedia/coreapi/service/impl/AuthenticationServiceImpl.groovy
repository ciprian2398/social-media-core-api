package com.socialmedia.coreapi.service.impl

import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.UserRepository
import com.socialmedia.coreapi.service.AuthenticationService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono;

@Service
class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository

    AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

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

    @Override
    Mono<User> getPrincipalUser() {
        userRepository.findBySubject(getPrincipalSub())
    }
}