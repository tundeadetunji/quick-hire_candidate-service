package io.github.tundeadetunji.candidate_service.repository;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}
