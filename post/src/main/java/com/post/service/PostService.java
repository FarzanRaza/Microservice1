package com.post.service;

import com.post.entity.Post;
import com.post.payload.PostDto;

public interface PostService {
    Post saveRecords(Post post);

    Post getRecord(String postId);

    PostDto getAllCommentForPost(String postId);
}
