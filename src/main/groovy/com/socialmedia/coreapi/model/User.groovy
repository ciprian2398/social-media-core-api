package com.socialmedia.coreapi.model


import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
public class User {

    @Id
    ObjectId id
    String name;
    String email;
}