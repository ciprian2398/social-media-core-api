package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.dto.UserDTO;
import com.socialmedia.coreapi.model.User;
import com.socialmedia.coreapi.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService
    }

    @Operation(summary = "Create a new user")
    @ApiResponses([
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "409", description = "Conflict - User already exists")
    ])
    @PostMapping
    ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        ResponseEntity.status(HttpStatus.CREATED).body("User created successfully")
    }


    @Operation(summary = "Edit an existing user")
    @ApiResponses([
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    ])
    @PutMapping("/{userId}")
    ResponseEntity<String> editUser(@PathVariable("userId") Long userId, @RequestBody User updatedUser) {
        if (userService.userExists(userId)) {
            userService.updateUser(updatedUser);
            ResponseEntity.status(HttpStatus.OK).body("User updated successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
        }
    }


    @Operation(summary = "Delete an existing user")
    @ApiResponses([
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    ])
    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        if (userService.userExists(userId)) {
            userService.markUserAsDeleted(userId)
            ResponseEntity.status(HttpStatus.OK).body("User deleted successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
        }
    }
}


