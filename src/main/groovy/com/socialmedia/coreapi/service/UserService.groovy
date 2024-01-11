package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.UserDTO;
import com.socialmedia.coreapi.model.User
import org.springframework.security.oauth2.jwt.Jwt;

interface UserService {
    User createUser(UserDTO user)

    boolean userExists(Long userId)

    void updateUser(User updatedUser)

    void markUserAsDeleted(Long userId)

    void createUserFromJWT(Jwt jwt)
}
