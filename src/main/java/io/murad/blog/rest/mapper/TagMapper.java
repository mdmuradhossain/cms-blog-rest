package io.murad.blog.rest.mapper;

import io.murad.blog.rest.dto.TagDto;
import io.murad.blog.rest.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mappings({
            @Mapping(target = "id",source = "tag.tagId"),
            @Mapping(target = "name",source = "tag.tagName")
    })
    TagDto mapToTagDto(Tag tag);

    @Mappings({
            @Mapping(target = "tagId", source = "tagDto.id"),
            @Mapping(target = "tagName", source = "tagDto.name"),
            @Mapping(target = "posts", source = "true")
    })
    Tag mapToTag(TagDto tagDto);

}
