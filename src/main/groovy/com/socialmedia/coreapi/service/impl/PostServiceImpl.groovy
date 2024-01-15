package com.socialmedia.coreapi.service.impl

import com.socialmedia.coreapi.dto.PostDTO
import com.socialmedia.coreapi.model.Post
import com.socialmedia.coreapi.model.User
import com.socialmedia.coreapi.repository.PostRepository
import com.socialmedia.coreapi.repository.UserRepository
import com.socialmedia.coreapi.service.PostService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostServiceImpl implements PostService {

    private PostRepository postRepository
    private UserRepository userRepository

    PostServiceImpl(PostRepository postRepository,
                    UserRepository userRepository) {
        this.postRepository = postRepository
        this.userRepository = userRepository
    }

    @Override
    Mono<Post> createPost(PostDTO postDTO, String sub) {
        userRepository.findBySubject(sub)
                .flatMap(user -> {
                    Post post = new Post();
                    post.setUserId(user.getId())
                    post.setContent(postDTO.getContent())
                    postRepository.save(post)
                })
    }

    @Override
    Mono<Post> updatePost(PostDTO postDTO, String postId, String sub) {
        postRepository.findById(postId)
                .zipWith(userRepository.findBySubject(sub))
                .filter((tuple) -> tuple.t1.userId == tuple.t2.getId())
                .flatMap(tuple -> {
                    tuple.t1.setContent(postDTO.getContent())
                    postRepository.save(tuple.t1)
                })
    }

    @Override
    Mono<Void> deletePost(String postId, String sub) {
        postRepository.findById(postId)
                .zipWith(userRepository.findBySubject(sub))
                .filter((tuple) -> tuple.t1.userId == tuple.t2.getId())
                .flatMap(tuple -> {
                    postRepository.delete(tuple.t1)
                })
    }

    @Override
    Mono<Void> likePost(Mono<User> user, String postId) {
        user.zipWith(postRepository.findById(postId))
                .flatMap(tuple -> {
                    tuple.t2.likes.add(new Post.Like(tuple.t1.getId()))
                    postRepository.save(tuple.t2)
                }).then()
    }

    @Override
    Mono<Void> unlikePost(Mono<User> user, String postId) {
        user.zipWith(postRepository.findById(postId))
                .flatMap(tuple -> {
                    tuple.t2.likes.removeIf(like -> like.userId == tuple.t1.getId())
                    postRepository.save(tuple.t2)
                }).then()
    }
}
