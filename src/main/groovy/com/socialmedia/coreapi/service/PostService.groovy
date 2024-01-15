package com.socialmedia.coreapi.service

import com.socialmedia.coreapi.dto.PostDTO
import com.socialmedia.coreapi.model.Post
import reactor.core.publisher.Mono

interface PostService {

    Mono<Post> createPost(PostDTO postDTO, String sub)

    Mono<Post> updatePost(PostDTO postDTO, String postId, String sub)

    Mono<Void> deletePost(String postId, String sub)
}