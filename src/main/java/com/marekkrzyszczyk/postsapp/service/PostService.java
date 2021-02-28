package com.marekkrzyszczyk.postsapp.service;

import com.marekkrzyszczyk.postsapp.model.dto.CustomMapper;
import com.marekkrzyszczyk.postsapp.model.dto.PostDto;
import com.marekkrzyszczyk.postsapp.model.entity.Post;
import com.marekkrzyszczyk.postsapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CustomMapper customMapper;

    @Autowired
    public PostService(PostRepository postRepository, CustomMapper customMapper) {
        this.postRepository = postRepository;
        this.customMapper = customMapper;
    }

    public List<Post> saveAll(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    public void deleteById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setDeleted(true);
            postRepository.save(post);
        }
    }

    public Post updateTitleAndBodyByPostId(Long id, String title, String body) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = new Post();
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            if (title != null) {
                post.setTitle(title);
            }
            if (body != null) {
                post.setBody(body);
            }
            post.setEdited(true);
            postRepository.save(post);
        }
       return post;
    }

    public List<PostDto> listAllNotDeleted() {
        return customMapper.mapPosts(postRepository.findByDeletedFalse());
    }

    public List<PostDto> listAllNotDeleted(String sorting) {
        List<Post> posts;
        if (sorting.equalsIgnoreCase("ASC")) {
            posts = postRepository.findByDeletedFalse(Sort.by(Sort.Order.asc("title")));
        } else if (sorting.equalsIgnoreCase("DESC")) {
            posts = postRepository.findByDeletedFalse(Sort.by(Sort.Order.desc("title")));
        } else {
            return listAllNotDeleted();
        }

        return customMapper.mapPosts(posts);
    }
}