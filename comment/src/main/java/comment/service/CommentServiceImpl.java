package comment.service;

import comment.entity.Comment;
import comment.entity.Post;

import comment.repository.CommentRepository;
import comment.restTemplate.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private RestTemplateConfig restTemplate;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveRecords(Comment comment) {
        Post forObject = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts/"+comment.getPostId(), Post.class);

    if(forObject!=null){
        comment.setCommentId(UUID.randomUUID().toString());
         return commentRepository.save(comment);

    }else{
        return null;
    }

    }

    @Override
    public List<Comment> getAllCommentByPostId(String postId) {
        List<Comment> allCommentsByPostId = commentRepository.findAllCommentsByPostId(postId);
        return allCommentsByPostId;
    }
}
