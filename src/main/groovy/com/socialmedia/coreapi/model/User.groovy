package com.socialmedia.coreapi.model

import groovy.transform.Canonical
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Canonical
@Document(collection = "users")
class User {
    @Id
    ObjectId id
    String subject
    String username
    String givenName
    String familyName
    String email
    List<ObjectId> following = []
}