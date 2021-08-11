package io.murad.blog.rest.mapper;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.Tag;
import io.murad.blog.rest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "tag", source = "tag"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())"),
            @Mapping(target = "postContent", source = "postRequest.content")
    })
    public Post mapToPost(PostRequest postRequest, Category category, Tag tag, User user);

}
