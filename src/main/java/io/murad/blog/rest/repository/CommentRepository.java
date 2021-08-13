package io.murad.blog.rest.repository;

import io.murad.blog.rest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
