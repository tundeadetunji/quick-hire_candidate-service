package io.github.tundeadetunji.candidate_service.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataPreloader {

    @PostConstruct
    public void preload() {
        // skipped in prod
    }
}