package io.murad.blog.rest.repository;

import io.murad.blog.rest.model.Comment;
import io.murad.blog.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
}
