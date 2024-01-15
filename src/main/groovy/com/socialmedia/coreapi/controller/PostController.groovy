package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.dto.PostDTO
import com.socialmedia.coreapi.mapper.PostMapper
import com.socialmedia.coreapi.service.AuthenticationService
import com.socialmedia.coreapi.service.PostService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Tag(name = "Post", description = "Post management APIs")
@RestController
@RequestMapping("/api/v1/posts")
class PostController {

    private PostMapper postMapper
    private PostService postService
    private AuthenticationService authenticationService;

    PostController(PostMapper postMapper,
                   PostService postService,
                   AuthenticationService authenticationService) {
        this.postMapper = postMapper
        this.postService = postService
        this.authenticationService = authenticationService
    }

    @PostMapping
    Mono<ResponseEntity> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO.getContent(), authenticationService.getPrincipalSub())
                .map(postMapper::mapToPostDto)
                .map(ResponseEntity::ok)
    }

    @PutMapping("/{postId}")
    Mono<ResponseEntity> updatePost(@RequestBody PostDTO postDTO, @PathVariable("postId") String postId) {
        postService.updatePost(postDTO.getContent(), postId, authenticationService.getPrincipalSub())
                .map(postMapper::mapToPostDto)
                .map(ResponseEntity::ok)
    }

    @DeleteMapping("/{postId}")
    Mono<ResponseEntity> deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId, authenticationService.getPrincipalSub())
                .map(ResponseEntity::ok)
    }

    @PostMapping("/{postId}/like")
    Mono<ResponseEntity> likePost(@PathVariable("postId") String postId) {
        postService.likePost(authenticationService.getPrincipalUser(), postId)
                .map(ResponseEntity::ok)
    }

    @PostMapping("/{postId}/unlike")
    Mono<ResponseEntity> unlikePost(@PathVariable("postId") String postId) {
        postService.unlikePost(authenticationService.getPrincipalUser(), postId)
                .map(ResponseEntity::ok)
    }
}


