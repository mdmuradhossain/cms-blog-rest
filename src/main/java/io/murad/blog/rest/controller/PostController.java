package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        postService.savePost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
