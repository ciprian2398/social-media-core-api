package com.socialmedia.coreapi.repository

import com.socialmedia.coreapi.model.Post
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findByUserId(ObjectId userId)

    Flux<Post> findByUserIdIn(List<ObjectId> userIds)
}