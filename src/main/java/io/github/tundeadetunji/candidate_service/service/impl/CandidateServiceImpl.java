package io.github.tundeadetunji.candidate_service.service.impl;

import io.github.tundeadetunji.candidate_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.candidate_service.domain.model.Job;
import io.github.tundeadetunji.candidate_service.service.CandidateService;
import io.github.tundeadetunji.candidate_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.candidate_service.messaging.NotificationProducer;
import io.github.tundeadetunji.candidate_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.strategy.CandidateStrategy;
import io.github.tundeadetunji.candidate_service.shared.NotificationMessage;
import io.github.tundeadetunji.candidate_service.strategy.JobStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.CANDIDATE_NOT_FOUND;
import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.JOB_NOT_FOUND;
import static io.github.tundeadetunji.candidate_service.constants.InlineStrings.CANDIDATE_REGISTERED;
import static io.github.tundeadetunji.candidate_service.constants.InlineStrings.NEW_APPLICATION;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateStrategy candidateStrategy;
    private final JobStrategy jobStrategy;
    private final NotificationProducer notificationProducer;

    @Override
    public Candidate registerAccount(Candidate candidate) {
        Candidate saved = candidateStrategy.save(candidate);

        //should send message to user's email in real prod
        //say, send notification to dedicated microservice by RabbitMq
        //but omitted for brevity in this mock

        notificationProducer.send(NotificationMessage.builder()
                .subject(CANDIDATE_REGISTERED)
                .body("New candidate registered: " + saved.getFirstName())
                .build());

        return saved;
    }

    @Override
    public Candidate updateAccount(Long id, Candidate candidate) {
        Candidate updated = candidateStrategy.update(id, candidate);

        //should send message to user's email in real prod
        //say, send notification to dedicated microservice by RabbitMq
        //but omitted for brevity in this mock

        return updated;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Candidate candidate = candidateStrategy.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANDIDATE_NOT_FOUND));

        boolean deleted = candidateStrategy.deleteById(id);

        //if (deleted)
        //should send message to user's email in real production
        //say, send notification to dedicated microservice by RabbitMq
        //but omitted for brevity in this mock

        return deleted;
    }

    @Override
    public Candidate viewAccount(String email) {
        return candidateStrategy.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(CANDIDATE_NOT_FOUND));
    }

    @Override
    public List<CandidateApplication> viewApplications(Long candidateId) {
        Candidate candidate = candidateStrategy.findById(candidateId)
                .orElseThrow(() -> new RecordNotFoundException(CANDIDATE_NOT_FOUND));

        return candidate.getApplications();
    }

    @Override
    public CandidateApplication applyToJob(Long postId, Long candidateId, Long jobId) {
        Candidate candidate = candidateStrategy.findById(candidateId)
                .orElseThrow(() -> new RecordNotFoundException(CANDIDATE_NOT_FOUND));

        Job job = jobStrategy.findById(jobId)
                .orElseThrow(() -> new RecordNotFoundException(JOB_NOT_FOUND));

        CandidateApplication application = CandidateApplication.builder()
                .applicationStatus(ApplicationStatus.APPLIED)
                .jobId(jobId)
                .build();

        if (candidate.getApplications() == null) {
            candidate.setApplications(new ArrayList<>());
        }

        candidate.getApplications().add(application);
        candidateStrategy.save(candidate); // re-persist candidate with updated applications

        // Notify recruiter
        notificationProducer.send(NotificationMessage.builder()
                .subject(NEW_APPLICATION)
                .body("Candidate " + candidate.getFirstName() + " has applied for your job post " + postId + ".")
                .build());

        return application;
    }
}
//.candidateId(candidate.getId())
