package io.murad.blog.rest.repository;

import io.murad.blog.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
