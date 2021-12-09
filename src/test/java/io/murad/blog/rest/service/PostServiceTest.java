package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.PostRequest;
import io.murad.blog.rest.dto.PostResponse;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.model.Post;
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

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        Post post = Post.builder()
                .postId(1L)
                .build();
        Mockito.when(postRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(post));

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