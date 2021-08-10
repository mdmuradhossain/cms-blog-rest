package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.TagDto;
import io.murad.blog.rest.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDto tagDto) {
        tagService.saveTag(tagDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.getTag(id), HttpStatus.OK);
    }
}
