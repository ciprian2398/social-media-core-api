package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.model.User
import org.springframework.security.oauth2.jwt.Jwt
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    Mono<User> createUserFromJwtJsonPayload(Jwt jwt)

    Flux<User> findAllUsers()

    Mono<Void> follow(Mono<User> userWhichFollows, String userToBeFollowed)

    Mono<Void> unfollow(Mono<User> userWhichFollows, String userToBeFollowed)
}
