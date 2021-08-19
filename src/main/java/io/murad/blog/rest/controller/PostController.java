package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.dto.PostResponse;
import io.murad.blog.rest.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        postService.savePost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(postService.getAllPostsByUsername(userName), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
