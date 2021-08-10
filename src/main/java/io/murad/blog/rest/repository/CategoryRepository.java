package io.murad.blog.rest.repository;

import io.murad.blog.rest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
