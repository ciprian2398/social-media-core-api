package com.socialmedia.coreapi.controller

import com.socialmedia.coreapi.dto.ContentWrapper
import com.socialmedia.coreapi.dto.FullDetailsPostDTO
import com.socialmedia.coreapi.mapper.PostMapper
import com.socialmedia.coreapi.service.AuthenticationService
import com.socialmedia.coreapi.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
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

    @Operation(summary = "Create a post")
    @PostMapping
    Mono<ResponseEntity> createPost(@RequestBody ContentWrapper contentWrapper) {
        postService.createPost(contentWrapper.getContent(), authenticationService.getPrincipalSub())
                .map(postMapper::mapToPostDto)
                .map(ResponseEntity::ok)
    }

    @Operation(summary = "Edit post")
    @PutMapping("/{postId}")
    Mono<ResponseEntity> updatePost(@RequestBody ContentWrapper contentWrapper, @PathVariable("postId") String postId) {
        postService.updatePost(contentWrapper.getContent(), postId, authenticationService.getPrincipalSub())
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

    @PostMapping("/{postId}/comment")
    Mono<ResponseEntity> commentOnPost(@RequestBody ContentWrapper contentWrapper, @PathVariable("postId") String postId) {
        postService.commentOnPost(authenticationService.getPrincipalUser(), postId, contentWrapper.getContent())
                .map(ResponseEntity::ok)
    }

    @Operation(summary = "Get authenticated user's feed")
    @GetMapping("myfeed")
    Flux<FullDetailsPostDTO> getMyFeed() {
        postService.getMyFeed(authenticationService.getPrincipalUser())
                .map(postMapper::mapToPostDto)
    }

    @Operation(summary = "Get random user's feed")
    @GetMapping("feed")
    Flux<FullDetailsPostDTO> getOtherFeed(@RequestParam("userId") String userId) {
        postService.getOtherFeed(userId)
                .map(postMapper::mapToPostDto)
    }
}


