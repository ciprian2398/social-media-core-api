package com.socialmedia.coreapi.repository

import com.socialmedia.coreapi.model.Post
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository extends ReactiveMongoRepository<Post, String> {

}