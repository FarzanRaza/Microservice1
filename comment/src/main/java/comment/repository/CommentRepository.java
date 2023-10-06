package comment.repository;

import comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {
    public List<Comment> findAllCommentsByPostId(String postId);
}
