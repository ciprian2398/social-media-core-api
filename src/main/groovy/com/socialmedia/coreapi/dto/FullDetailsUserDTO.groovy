package com.socialmedia.coreapi.dto

import groovy.transform.Canonical
import org.bson.types.ObjectId

@Canonical
class FullDetailsUserDTO {
    String id
    String subject
    String username
    String givenName
    String familyName
    String email
    List<ObjectId> following = []
}