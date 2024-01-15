package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.dto.FullDetailsUserDTO
import com.socialmedia.coreapi.mapper.UserMapper
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

    private UserMapper userMapper
    private UserService userService
    private AuthenticationService authenticationService;

    UserController(UserMapper userMapper,
                   UserService userService,
                   AuthenticationService authenticationService) {
        this.userMapper = userMapper
        this.userService = userService
        this.authenticationService = authenticationService
    }

    @GetMapping
    Flux<FullDetailsUserDTO> getAllUsers2() {
        userService.findAllUsers()
                .map(userMapper::mapToFullDetailsUserDto)
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


