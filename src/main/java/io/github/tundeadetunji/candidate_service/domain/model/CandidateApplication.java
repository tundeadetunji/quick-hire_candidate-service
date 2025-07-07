package io.github.tundeadetunji.candidate_service.domain.model;

import io.github.tundeadetunji.candidate_service.domain.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import static io.github.tundeadetunji.candidate_service.constants.ExceptionMessages.CLONE_NOT_SUPPORTED;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId; // for display/search if needed

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Version
    private Long version;


    public static CandidateApplication of(Candidate candidate, Post post, Long jobId) {
        return CandidateApplication.builder()
                .candidate(candidate)
                .post(post)
                .jobId(jobId)
                .applicationStatus(ApplicationStatus.APPLIED)
                .build();
    }
}
