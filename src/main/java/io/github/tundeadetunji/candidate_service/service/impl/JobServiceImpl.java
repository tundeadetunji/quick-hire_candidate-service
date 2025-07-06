package io.github.tundeadetunji.candidate_service.service.impl;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.domain.model.Job;
import io.github.tundeadetunji.candidate_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.candidate_service.service.JobService;
import io.github.tundeadetunji.candidate_service.strategy.JobStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.CANDIDATE_NOT_FOUND;
import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.JOB_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobStrategy jobStrategy;

    @Override
    public Job getJob(Long id) {
        return jobStrategy.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(JOB_NOT_FOUND));
    }
}
