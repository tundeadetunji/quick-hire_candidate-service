package io.github.tundeadetunji.candidate_service.strategy.impl;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.repository.CandidateRepository;
import io.github.tundeadetunji.candidate_service.strategy.CandidateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Profile("h2") // Only active in 'h2' profile
@RequiredArgsConstructor
public class CandidateStrategyImpl implements CandidateStrategy {

    private final CandidateRepository repository;

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<Candidate> findAll() {
        return repository.findAll();
    }

    @Override
    public Candidate update(Long id, Candidate candidate) {
        //✔redo
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.findById(id)
                .map(c -> {
                    repository.delete(c);
                    return true;
                })
                .orElse(false);
    }
}
