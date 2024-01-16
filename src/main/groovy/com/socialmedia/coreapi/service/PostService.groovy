package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.model.Post
import com.socialmedia.coreapi.model.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PostService {

    Mono<Post> createPost(String content, String sub)

    Mono<Post> updatePost(String content, String postId, String sub)

    Mono<Void> deletePost(String postId, String sub)

    Mono<Void> likePost(Mono<User> user, String postId)

    Mono<Void> unlikePost(Mono<User> user, String postId)

    Mono<Void> commentOnPost(Mono<User> user, String postId, String comment)

    Flux<Post> getMyFeed(Mono<User> userMono)

    Flux<Post> getOtherFeed(String userId)
}