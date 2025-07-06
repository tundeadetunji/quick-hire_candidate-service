package io.github.tundeadetunji.candidate_service.strategy.impl;

import io.github.tundeadetunji.candidate_service.domain.model.Post;
import io.github.tundeadetunji.candidate_service.repository.PostRepository;
import io.github.tundeadetunji.candidate_service.strategy.PostStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostStrategyImpl implements PostStrategy {

    private final PostRepository postRepository;

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findJobPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post saveJobPost(Post post) {
        return postRepository.save(post);
    }

}
