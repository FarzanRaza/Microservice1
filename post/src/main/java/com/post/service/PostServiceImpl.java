package com.post.service;

import com.post.entity.Post;
import com.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post saveRecords(Post post) {
        post.setId(UUID.randomUUID().toString());
        return   postRepository.save(post);

    }

    @Override
    public Post getRecord(String postId) {
        return postRepository.findById(postId).get();
    }
}
