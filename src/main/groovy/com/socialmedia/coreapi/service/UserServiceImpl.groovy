package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.UserDTO
import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName())
        user.setEmail(userDTO.getEmail())
        userRepository.save(user);
    }

    @Override
    boolean userExists(Long userId) {
        userRepository.existsById(userId);
    }

    @Override
    void updateUser(User updatedUser) {

    }

    @Override
    void markUserAsDeleted(Long userId) {

    }
}
