package io.github.tundeadetunji.candidate_service.service;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.domain.model.CandidateApplication;

import java.util.List;

public interface CandidateService {
    Candidate registerAccount(Candidate candidate);
    Candidate updateAccount(Long id, Candidate candidate);
    boolean deleteAccount(Long id);
    Candidate viewAccount(String email);
    List<CandidateApplication> viewApplications(Long candidateId);
    CandidateApplication applyToJob(Long postId, Long candidateId, Long jobId);
}

