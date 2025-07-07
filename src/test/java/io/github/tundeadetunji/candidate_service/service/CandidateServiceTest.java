package io.github.tundeadetunji.candidate_service.service;

import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.messaging.NotificationProducer;
import io.github.tundeadetunji.candidate_service.service.impl.CandidateServiceImpl;
import io.github.tundeadetunji.candidate_service.strategy.CandidateStrategy;
import io.github.tundeadetunji.candidate_service.strategy.JobStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CandidateServiceTest {

    @Test
    void registerAccount_shouldSaveCandidateAndNotify() {

        JobStrategy jobStrategy = mock(JobStrategy.class);
        CandidateStrategy candidateStrategy = mock(CandidateStrategy.class);
        NotificationProducer producer = mock(NotificationProducer.class);
        CandidateService service = new CandidateServiceImpl(candidateStrategy, jobStrategy, producer);

        Candidate input = Candidate.builder()
                .firstName("Liz")
                .lastName("Benson")
                .email("lizb@hotmail.com")
                .build();

        when(candidateStrategy.save(any())).thenReturn(input);

        Candidate result = service.registerAccount(input);

        assertEquals("lizb@hotmail.com", result.getEmail());
        verify(producer).send(any());
    }
}
