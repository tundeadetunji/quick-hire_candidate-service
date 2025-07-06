package io.github.tundeadetunji.candidate_service.repository;

import io.github.tundeadetunji.candidate_service.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
