package com.marekkrzyszczyk.postsapp.service;

import com.marekkrzyszczyk.postsapp.model.entity.Post;
import com.marekkrzyszczyk.postsapp.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    private Post post1 = new Post(1L, "title", "some content", 2L);
    private Post post2 = new Post(2L, "next title", "some other content", 1L);
    private Optional<Post> optionalPost1 = Optional.of(post1);
    private Optional<Post> optionalPost2 = Optional.of(post2);

    @Test
    void deleteByIdTest() {
        when(postRepository.findById(1L)).thenReturn(optionalPost1);
        postService.deleteById(1L);
        assertEquals(true, optionalPost1.get().getDeleted());
    }

    @Test
    void updateByIdTest() {
        when(postRepository.findById(2L)).thenReturn(optionalPost2);
        Post updatedPost = postService.updateTitleAndBodyByPostId(2L, "new title", "new body");
        assertEquals(true, updatedPost.getEdited());
        assertEquals("new title", updatedPost.getTitle());
        assertEquals("new body", updatedPost.getBody());
    }

}