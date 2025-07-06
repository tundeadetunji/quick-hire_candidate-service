package io.github.tundeadetunji.candidate_service.repository;

import io.github.tundeadetunji.candidate_service.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
