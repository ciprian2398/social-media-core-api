package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.PostDTO
import com.socialmedia.coreapi.model.Post
import com.socialmedia.coreapi.model.User
import reactor.core.publisher.Mono

interface PostService {

    Mono<Post> createPost(String content, String sub)

    Mono<Post> updatePost(String content, String postId, String sub)

    Mono<Void> deletePost(String postId, String sub)

    Mono<Void> likePost(Mono<User> user, String postId)

    Mono<Void> unlikePost(Mono<User> user, String postId)
}