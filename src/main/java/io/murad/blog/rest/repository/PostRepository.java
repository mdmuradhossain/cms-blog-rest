package io.murad.blog.rest.repository;

import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUser(User user);
}
