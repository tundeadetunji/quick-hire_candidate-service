package io.github.tundeadetunji.candidate_service.strategy;

import io.github.tundeadetunji.candidate_service.domain.model.Job;

import java.util.Optional;

public interface JobStrategy {
    Optional<Job> findById(Long id);
}
