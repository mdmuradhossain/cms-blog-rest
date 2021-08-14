package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.CommentDto;
import io.murad.blog.rest.exception.PostNotFoundException;
import io.murad.blog.rest.mapper.CommentMapper;
import io.murad.blog.rest.model.Comment;
import io.murad.blog.rest.model.NotificationEmail;
import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.repository.CommentRepository;
import io.murad.blog.rest.repository.PostRepository;
import io.murad.blog.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {

    private static final String POST_URL = " ";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final MailService mailService;
    private final CommentMapper commentMapper;
    private final MailContentBuilder mailContentBuilder;

    public Comment saveComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new PostNotFoundException((commentDto.getPostId().toString())));
        User user = authService.getCurrentUser();
        Comment comment = commentMapper.mapToComment(commentDto, post, user);
        commentRepository.save(comment);

        //Send message for comment
        String message = mailContentBuilder.build(post.getUser().getUserName() + " posted a comment on your post." + post.getPostTitle() + POST_URL);
        sendCommentNotification(message, post.getUser());
        return comment;
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUserName() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getCommentsForSinglePost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post).stream()
                .map((commentMapper::mapToDto)).collect(Collectors.toList());
    }

}
