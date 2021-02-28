package com.marekkrzyszczyk.postsapp.controller;

import com.marekkrzyszczyk.postsapp.model.entity.Post;
import com.marekkrzyszczyk.postsapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private static final String GET_ALL_POSTS = "https://jsonplaceholder.typicode.com/posts";

    PostService postService;
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public PostController(RestTemplateBuilder restTemplateBuilder, PostService postService) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.postService = postService;
    }

    @GetMapping("/callGetAllPosts")
    public List<Post> callGetAllPosts() {
        Post[] postsArray = restTemplateBuilder.build().getForObject(GET_ALL_POSTS, Post[].class);
        return Arrays.asList(postsArray);
    }

    @GetMapping("/posts")
    public List<Post> listAllPosts(@RequestParam(required = false) String sortByTitle) {
        List<Post> posts;
        if (sortByTitle != null) {
            posts = postService.listAllNotDeleted(sortByTitle);
        } else {
            posts = postService.listAllNotDeleted();
        }
        return posts;
    }

    @PostMapping("/posts")
    public List<Post> savePosts() {
        List<Post> posts = callGetAllPosts();
        return postService.saveAll(posts);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Long id, @RequestParam(required = false) String newTitle, @RequestParam(required = false) String newBody) {
        Post updatedPost = postService.updateTitleAndBodyByPostId(id, newTitle, newBody);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

}
