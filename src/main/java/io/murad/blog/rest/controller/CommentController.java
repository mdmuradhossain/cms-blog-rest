package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.CommentDto;
import io.murad.blog.rest.model.Comment;
import io.murad.blog.rest.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto){
        Comment comment = commentService.saveComment(commentDto);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
}
