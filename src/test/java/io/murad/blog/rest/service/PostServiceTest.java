package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.dto.PostResponse;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Post;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;
    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        User user = authService.getCurrentUser();
        Post post = Post.builder()
                .postId(1L)
                .postTitle("Java Programming Language")
                .postContent("Java is a high level programming language.")
                .user(user)
                .build();
        Mockito.when(postRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(post));
        assert post != null;
        Mockito.when(postRepository.save(post)).thenReturn(post);

    }

    @Test
    public void getPostByIdTest() {
        Long postId = 1L;
        PostResponse foundPost = postService.getPost(postId);
        assertEquals(postId, foundPost.getId());
    }

    @Test
    public void shouldSavePostToDB(){
        PostRequest post = PostRequest.builder()
                .categoryName("Java")
                .title("Java Programming Language")
                .content("Java is a high level programming language.")
                .build();
        Post savedPost = postService.savePost(post);
        assertEquals(post,savedPost);
    }
}