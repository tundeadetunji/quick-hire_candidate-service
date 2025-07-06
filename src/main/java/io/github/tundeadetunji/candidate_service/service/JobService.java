package io.github.tundeadetunji.candidate_service.service;

import io.github.tundeadetunji.candidate_service.domain.model.Job;

public interface JobService {
    Job getJob(Long id);
}
