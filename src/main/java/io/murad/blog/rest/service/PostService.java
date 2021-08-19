package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.dto.PostResponse;
import io.murad.blog.rest.exception.CategoryNotFoundException;
import io.murad.blog.rest.exception.PostNotFoundException;
import io.murad.blog.rest.mapper.PostMapper;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.Tag;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.repository.CategoryRepository;
import io.murad.blog.rest.repository.PostRepository;
import io.murad.blog.rest.repository.TagRepository;
import io.murad.blog.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void savePost(PostRequest postRequest) {
        Category category = categoryRepository.findByCategoryName(postRequest.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException("Category Not Found " + postRequest.getCategoryName()));
        User currentUser = authService.getCurrentUser();
        postRepository.save(postMapper.mapToPost(postRequest, category, currentUser));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToPostDto).collect(Collectors.toList());
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found " + id.toString()));
        return postMapper.mapToPostDto(post);
    }

    public List<PostResponse> getAllPostsByUsername(String userName) {
        // User user = userRepository.findByUserName(userName);
        return postRepository.findAllByUser(userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(userName)))
                .stream()
                .map(postMapper::mapToPostDto).collect(Collectors.toList());
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
