package io.murad.blog.rest.dto;

import io.murad.blog.rest.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String categoryName;
    private Instant creationTime;
    private List<Tag> tagNames;
//    private List<TagDto> tagNames;
}
