package com.socialmedia.coreapi.mapper

import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class ObjectIdMapper {

    String map(ObjectId value) {
        value != null ? value.toString() : null
    }

    ObjectId map(String value) {
        value != null ? new ObjectId(value) : null
    }
}
