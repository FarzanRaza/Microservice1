package com.post.service;

import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.repository.PostRepository;
import com.post.restTemplate.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Override
    public Post saveRecords(Post post) {
        post.setId(UUID.randomUUID().toString());
        return   postRepository.save(post);

    }

    @Override
    public Post getRecord(String postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public PostDto getAllCommentForPost(String postId) {
        ArrayList forObject = restTemplateConfig.getRestTemplate().getForObject("http://localhost:8082/api/comments/"+postId, ArrayList.class);
        Post post = postRepository.findById(postId).get();

        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setComments(forObject);
        return postDto;
    }
}
