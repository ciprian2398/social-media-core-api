package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.UserDTO;
import com.socialmedia.coreapi.model.User;

interface UserService {
    User createUser(UserDTO user);

    boolean userExists(Long userId);

    void updateUser(User updatedUser);

    void markUserAsDeleted(Long userId);
}
