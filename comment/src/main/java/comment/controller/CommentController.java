package comment.controller;

import comment.entity.Comment;
import comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment comment1 = commentService.saveRecords(comment);
        return new ResponseEntity<>(comment1, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getAllCommentByPostId(@PathVariable String postId){
        List<Comment> allCommentByPostId = commentService.getAllCommentByPostId(postId);
        return  new ResponseEntity<>(allCommentByPostId,HttpStatus.OK);
    }
}
