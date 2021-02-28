package com.marekkrzyszczyk.postsapp.service;

import com.marekkrzyszczyk.postsapp.model.dto.CustomMapper;
import com.marekkrzyszczyk.postsapp.model.dto.PostDto;
import com.marekkrzyszczyk.postsapp.model.entity.Post;
import com.marekkrzyszczyk.postsapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void saveAll(List<Post> posts) {
        for (Post post : posts) {
            Optional<Post> optionalPost = postRepository.findById(post.getId());
            if (optionalPost.isPresent()) {
                Post retrievedPost = optionalPost.get();
                if (!retrievedPost.getDeleted() && !retrievedPost.getEdited()) {
                    retrievedPost.setTitle(post.getTitle());
                    retrievedPost.setBody(post.getBody());
                    retrievedPost.setUserId(post.getUserId());
                }
            }
            else {
                postRepository.save(post);
            }
        }
    }

        public void deleteById (Long id){
            Optional<Post> optionalPost = postRepository.findById(id);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setDeleted(true);
                postRepository.save(post);
            }
        }

        public Post updateTitleAndBodyByPostId (Long id, String title, String body){
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

        public List<PostDto> listAllNotDeleted () {
            return customMapper.mapPosts(postRepository.findByDeletedFalse());
        }

        public List<PostDto> listAllNotDeleted (String sorting){
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