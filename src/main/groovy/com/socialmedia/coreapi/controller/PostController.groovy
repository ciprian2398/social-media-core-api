package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.dto.PostDTO
import com.socialmedia.coreapi.model.Post
import com.socialmedia.coreapi.service.AuthenticationService
import com.socialmedia.coreapi.service.PostService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Post", description = "Post management APIs")
@RestController
@RequestMapping("/api/v1/posts")
class PostController {

    private AuthenticationService authenticationFacade;

    private final PostService postService

    PostController(PostService postService,
                   AuthenticationService authenticationFacade) {
        this.postService = postService
        this.authenticationFacade = authenticationFacade
    }

    @PostMapping
    Mono<Post> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO, authenticationFacade.getPrincipalSub())
    }

    @PutMapping("/{postId}")
    Mono<Post> updatePost(@RequestBody PostDTO postDTO, @PathVariable("postId") String postId) {
        postService.updatePost(postDTO, postId, authenticationFacade.getPrincipalSub())
    }

    @DeleteMapping("/{postId}")
    Mono<Void> deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId, authenticationFacade.getPrincipalSub())
    }
}


