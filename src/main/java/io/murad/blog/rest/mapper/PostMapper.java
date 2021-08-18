package io.murad.blog.rest.mapper;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.dto.PostResponse;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.Tag;
import io.murad.blog.rest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mappings({
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "tags", expression = "java(tagsFromStrings(postRequest.getTagNames()))"),
            @Mapping(target = "user", source = "user"),
//            @Mapping(target = "tags",source = "postRequest.tagNames"),
            @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())"),
            @Mapping(target = "postTitle",source = "postRequest.title"),
            @Mapping(target = "postContent", source = "postRequest.content")
    })
    public abstract Post mapToPost(PostRequest postRequest, Category category, User user);

    @Mapping(target = "tagNames", expression = "java(post.getTags())")
//    @Mapping(target = "tagNames",source = "post.tags")
    @Mapping(target = "creationTime", source = "post.createdAt")
    @Mapping(target = "id",source = "post.postId")
    @Mapping(target = "title",source = "post.postTitle")
    @Mapping(target = "content", source = "post.postContent")
    @Mapping(target = "categoryName", source = "category.categoryName")
    public abstract PostResponse mapToPostDto(Post post);

    public List<Tag> tagsFromStrings(List<String> tagsAsString){
        List<Tag> tags = new ArrayList<>();
        if (tagsAsString != null){
            tags = tagsAsString.stream()
                    .map(tagString->{
                        Tag tag = new Tag();
                        tag.setTagName(tagString);
                        return tag;
                    }).collect(Collectors.toList());
        }
        return tags;
    }
}
