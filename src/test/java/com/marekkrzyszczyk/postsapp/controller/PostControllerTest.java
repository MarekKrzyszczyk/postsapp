package com.marekkrzyszczyk.postsapp.controller;

import com.marekkrzyszczyk.postsapp.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    private String id = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void listAllPostsTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/posts")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void deletePostTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/posts/{id}", id)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updatePostTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/posts/{id}", id)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }
}