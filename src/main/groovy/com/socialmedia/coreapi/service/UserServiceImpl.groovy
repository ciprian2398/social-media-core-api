package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.UserDTO
import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.UserRepository
import groovy.json.JsonSlurper
import org.springframework.security.oauth2.jwt.Jwt
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

    @Override
    void createUserFromJWT(Jwt jwt) {

        String[] chunks = jwt.tokenValue.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(payload)

        def user = new User();
        user.setEmail(object.name)
        user.setName(object.email)
        userRepository.save(user);
    }
}
