package com.socialmedia.coreapi.service;

import com.socialmedia.coreapi.model.User;

public interface UserService {
    User createUser(User user);

    boolean userExists(Long userId);

    void updateUser(User updatedUser);

    void markUserAsDeleted(Long userId);
}
