package com.socialmedia.coreapi.model

import groovy.transform.Canonical
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document(collection = "post")
class Post {
    @Id
    ObjectId id
    ObjectId userId
    String content
    List<Comment> comments = []
    List<Like> likes = []

    @Canonical
    static class Like {
        ObjectId userId
    }

    @Canonical
    static class Comment {
        ObjectId userId
        String comment
    }
}