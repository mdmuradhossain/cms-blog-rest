package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.mapper.PostMapper;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Tag;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.repository.CategoryRepository;
import io.murad.blog.rest.repository.PostRepository;
import io.murad.blog.rest.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void savePost(PostRequest postRequest) {
        Category category = categoryRepository.findByCategoryName(postRequest.getCategoryName()).orElseThrow(()-> new CategoryNotFoundException("Not Found"));
        User currentUser = authService.getCurrentUser();
        postRepository.save(postMapper.mapToPost(postRequest, category, currentUser));
    }
}
