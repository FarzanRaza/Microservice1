package com.post.service;

import com.post.entity.Post;

public interface PostService {
    Post saveRecords(Post post);

    Post getRecord(String postId);
}
