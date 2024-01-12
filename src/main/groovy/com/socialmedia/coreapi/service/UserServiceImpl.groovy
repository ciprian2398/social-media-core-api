package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.UserRepository
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    Flux<User> findAllUsers() {
        return userRepository.findAll()
    }

    @Override
    Mono<User> createUserFromJwtJsonPayload(Jwt jwt) {
        if (!userRepository.existsByEmail(jwt.getClaim("email")).block()) {
            def user = new User()
            user.setSubject(jwt.getClaim("sub"))
            user.setUsername(jwt.getClaim("preferred_username"))
            user.setGivenName(jwt.getClaim("given_name"))
            user.setFamilyName(jwt.getClaim("family_name"))
            user.setEmail(jwt.getClaim("email"))
            userRepository.save(user)
        }
    }
}
