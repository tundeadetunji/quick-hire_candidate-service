package io.github.tundeadetunji.candidate_service.strategy.impl;

import io.github.tundeadetunji.candidate_service.domain.model.Job;
import io.github.tundeadetunji.candidate_service.repository.JobRepository;
import io.github.tundeadetunji.candidate_service.strategy.JobStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobStrategyImpl implements JobStrategy {

    private final JobRepository repository;

    @Override
    public Optional<Job> findById(Long id) {
        return repository.findById(id);
    }
}
