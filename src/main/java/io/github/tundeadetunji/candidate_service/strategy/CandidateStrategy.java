package io.github.tundeadetunji.candidate_service.strategy;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateStrategy {
    Candidate save(Candidate candidate);
    Optional<Candidate> findById(Long id);
    Optional<Candidate> findByEmail(String email);
    List<Candidate> findAll();

    Candidate update(Long id, Candidate candidate);
    boolean deleteById(Long id);
}
