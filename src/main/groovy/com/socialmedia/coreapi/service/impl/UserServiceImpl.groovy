package com.socialmedia.coreapi.service.impl

import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.UserRepository
import com.socialmedia.coreapi.service.UserService
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    Flux<User> findAllUsers() {
        return userRepository.findAll()
    }

    @Override
    Mono<Void> follow(Mono<User> userWhichFollows, String userToBeFollowed) {
        userWhichFollows
                .zipWith(userRepository.findById(userToBeFollowed))
                .filter(tuple -> !tuple.t1.getId().equals(tuple.t2.getId()))
                .filter(tuple -> !tuple.t1.following.contains(tuple.t2.getId()))
                .flatMap(tuple -> {
                    tuple.t1.following.add(tuple.t2.getId())
                    userRepository.save(tuple.t1)
                }).then()
    }

    @Override
    Mono<Void> unfollow(Mono<User> userWhichFollows, String userToBeUnfollowed) {
        userWhichFollows
                .zipWith(userRepository.findById(userToBeUnfollowed))
                .filter(tuple -> !tuple.t1.getId().equals(tuple.t2.getId()))
                .filter(tuple -> tuple.t1.following.contains(tuple.t2.getId()))
                .flatMap(tuple -> {
                    tuple.t1.following.remove(tuple.t2.getId())
                    userRepository.save(tuple.t1)
                }).then()
    }

    @Override
    Mono<User> createUserFromJwtJsonPayload(Jwt jwt) {
        userRepository.existsByEmail(jwt.getClaim("email"))
                .filter(exists -> !exists)
                .flatMap(v -> {
                    def user = new User()
                    user.setSubject(jwt.getClaim("sub"))
                    user.setUsername(jwt.getClaim("preferred_username"))
                    user.setGivenName(jwt.getClaim("given_name"))
                    user.setFamilyName(jwt.getClaim("family_name"))
                    user.setEmail(jwt.getClaim("email"))
                    return userRepository.save(user)
                })
    }
}
