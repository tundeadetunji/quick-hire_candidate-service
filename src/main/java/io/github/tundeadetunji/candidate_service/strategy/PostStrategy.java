package io.github.tundeadetunji.candidate_service.strategy;

import io.github.tundeadetunji.candidate_service.domain.model.Post;

import java.util.Optional;

public interface PostStrategy {
    Post update(Post post);
    Optional<Post> findJobPostById(Long postId);
    Post saveJobPost(Post post);

}
