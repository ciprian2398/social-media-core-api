package com.socialmedia.coreapi.mapper

import com.socialmedia.coreapi.config.MapStructConfig
import com.socialmedia.coreapi.dto.FullDetailsUserDTO
import com.socialmedia.coreapi.model.User
import org.mapstruct.Mapper

@Mapper(uses = ObjectIdMapper.class, componentModel = "spring", config = MapStructConfig.class)
interface UserMapper {

    FullDetailsUserDTO mapToFullDetailsUserDto(User user);
}