package io.github.tundeadetunji.candidate_service.service.impl;

import io.github.tundeadetunji.candidate_service.domain.model.Post;
import io.github.tundeadetunji.candidate_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.candidate_service.exception.UnauthorizedAccessException;
import io.github.tundeadetunji.candidate_service.service.PostService;
import io.github.tundeadetunji.candidate_service.strategy.PostStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.POST_NOT_FOUND;
import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.UNAUTHORIZED_ACCESS;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostStrategy postStrategy;

    @Override
    public Post updatePost(Post post) {
        Post saved = postStrategy.findJobPostById(post.getId())
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        return postStrategy.saveJobPost(post);
    }

    @Override
    public Post findJobPost(Long postId, Long recruiterId) {
        Post post = postStrategy.findJobPostById(postId)
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        if (!post.getRecruiterId().equals(recruiterId)) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS);
        }

        return post;
    }
}
