package comment.service;

import comment.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment saveRecords(Comment comment);

    List<Comment> getAllCommentByPostId(String postId);
}
