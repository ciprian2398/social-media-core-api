package com.socialmedia.coreapi.dto

import groovy.transform.Canonical

@Canonical
class FullDetailsPostDTO {

    String id
    String userId
    String content
    List<Comment> comments = []
    List<Like> likes = []

    @Canonical
    static class Like {
        String userId
    }

    @Canonical
    static class Comment {
        String userId
        String comment
    }
}
