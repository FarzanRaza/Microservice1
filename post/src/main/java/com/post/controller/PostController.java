package com.post.controller;

import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.service.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "commentBreaker",fallbackMethod = "commentFallback")
    public  ResponseEntity<PostDto> getAllCommentForPost(@PathVariable String postId){
        PostDto allCommentForPost = postService.getAllCommentForPost(postId);
        return new ResponseEntity<>(allCommentForPost,HttpStatus.OK);
    }
    // same handler method as for getAllCommentForPost
    public ResponseEntity<PostDto> commentFallback(String postId,Exception ex){
        System.out.println("Fallback is executed because server is down:"+ex.getMessage());
        ex.printStackTrace();;
        PostDto postDto=new PostDto();
        postDto.setId("12345");
        postDto.setTitle("service down");
        postDto.setContent("service down");
        postDto.setDescription("service down");
        return new ResponseEntity<>(postDto,HttpStatus.BAD_REQUEST);
    }

}
