package com.marekkrzyszczyk.postsapp.model.dto;

import com.marekkrzyszczyk.postsapp.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {Utils.class}, componentModel = "spring")
public interface CustomMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    List<PostDto> mapPosts(List<Post> posts);

}
