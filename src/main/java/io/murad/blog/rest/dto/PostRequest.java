package io.murad.blog.rest.dto;

import io.murad.blog.rest.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String categoryName;
    private String title;
    private String content;
    private List<String> tagNames;
//    private List<TagDto> tagNames;
}
