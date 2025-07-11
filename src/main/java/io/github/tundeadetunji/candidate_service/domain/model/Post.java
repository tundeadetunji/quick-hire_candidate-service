package io.github.tundeadetunji.candidate_service.domain.model;

import io.github.tundeadetunji.candidate_service.domain.enums.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Job job;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    private Long recruiterId;

    private LocalDateTime postedOn;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateApplication> applications = new ArrayList<>();

    public static Post from(Job job, Long recruiterId) {
        return Post.builder()
                .job(job)
                .postStatus(PostStatus.ACTIVE)
                .recruiterId(recruiterId)
                .postedOn(LocalDateTime.now())
                .build();
    }
}
