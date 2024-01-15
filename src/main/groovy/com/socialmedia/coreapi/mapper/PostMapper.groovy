package com.socialmedia.coreapi.mapper

import com.socialmedia.coreapi.config.MapStructConfig
import com.socialmedia.coreapi.dto.FullDetailsPostDTO
import com.socialmedia.coreapi.model.Post
import org.mapstruct.Mapper

@Mapper(uses = ObjectIdMapper.class, componentModel = "spring", config = MapStructConfig.class)
interface PostMapper {

    FullDetailsPostDTO mapToPostDto(Post post);
}