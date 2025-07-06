package io.github.tundeadetunji.candidate_service.service;

import io.github.tundeadetunji.candidate_service.domain.model.Post;

public interface PostService {
    Post updatePost(Post post);
    Post findJobPost(Long postId, Long recruiterId);

}
