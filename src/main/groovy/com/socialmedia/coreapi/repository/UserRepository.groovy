package com.socialmedia.coreapi.repository

import com.socialmedia.coreapi.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends MongoRepository<User, String> {
    User findByName(String username)
}