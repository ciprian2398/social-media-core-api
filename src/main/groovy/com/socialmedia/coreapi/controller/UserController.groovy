package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    @GetMapping
    Flux<User> getAllUsers() {
        userService.findAllUsers()
    }
}


