package com.post.controller;

import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
   private PostService postService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        Post post1 = postService.saveRecords(post);
        return new ResponseEntity<>(post1, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getRecord(@PathVariable String postId){
        Post record = postService.getRecord(postId);
        return new ResponseEntity<>(record,HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public  ResponseEntity<PostDto> getAllCommentForPost(@PathVariable String postId){
        PostDto allCommentForPost = postService.getAllCommentForPost(postId);
        return new ResponseEntity<>(allCommentForPost,HttpStatus.OK);
    }

}
