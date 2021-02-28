package com.marekkrzyszczyk.postsapp.model.dto;

import com.marekkrzyszczyk.postsapp.model.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomMapperTest {

    private final CustomMapper customMapper;

    @Autowired
    public CustomMapperTest(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }

    private final Post post1 = new Post(1L, "title", "some content", 2L);
    private final Post post2 = new Post(2L, "next title", "some other content", 1L);

    List<Post> posts = new ArrayList<>(Arrays.asList(post1, post2));

    @Test
    public void mapPostsTest() {
        List<PostDto> postsDto = customMapper.mapPosts(posts);
        assertEquals(posts.size(), postsDto.size());
        assertEquals(posts.get(0).getId(), postsDto.get(0).getId());
        assertEquals(posts.get(0).getTitle(), postsDto.get(0).getTitle());
        assertEquals(posts.get(0).getBody(), postsDto.get(0).getBody());
        assertEquals(posts.get(1).getId(), postsDto.get(1).getId());
        assertEquals(posts.get(1).getTitle(), postsDto.get(1).getTitle());
        assertEquals(posts.get(1).getBody(), postsDto.get(1).getBody());
    }

}