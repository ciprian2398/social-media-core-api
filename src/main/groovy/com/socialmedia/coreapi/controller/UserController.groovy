package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.service.AuthenticationService
import com.socialmedia.coreapi.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService
    private AuthenticationService authenticationService;

    UserController(UserService userService,
                   AuthenticationService authenticationService) {
        this.userService = userService
        this.authenticationService = authenticationService
    }

    @GetMapping
    Flux<User> getAllUsers() {
        userService.findAllUsers()
    }

    @PostMapping("follow")
    Mono<ResponseEntity> follow(@RequestParam("otherUserId") String otherUserId) {
        userService.follow(authenticationService.getPrincipalUser(), otherUserId)
                .map(ResponseEntity::ok)
    }

    @PostMapping("unfollow")
    Mono<ResponseEntity> unfollow(@RequestParam("otherUserId") String otherUserId) {
        userService.unfollow(authenticationService.getPrincipalUser(), otherUserId)
                .map(ResponseEntity::ok)
    }
}


