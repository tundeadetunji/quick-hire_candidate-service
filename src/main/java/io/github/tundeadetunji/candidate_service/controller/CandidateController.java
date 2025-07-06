package io.github.tundeadetunji.candidate_service.controller;

import io.github.tundeadetunji.candidate_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.candidate_service.dto.CandidateDto;
import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.service.CandidateService;
import io.github.tundeadetunji.candidate_service.service.impl.CandidateServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @Operation(summary = "Register an account for candidate")
    @PostMapping
    public ResponseEntity<Candidate> registerAccount(@Valid @RequestBody CandidateDto dto) {
        return ResponseEntity.ok(service.registerAccount(Candidate.from(dto)));
    }

    @Operation(summary = "Update account for candidate")
    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateAccount(@PathVariable Long id, @Valid @RequestBody CandidateDto dto) {
        return ResponseEntity.ok(service.updateAccount(id, Candidate.from(dto)));
    }

    @Operation(summary = "Delete account of candidate")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }

    @Operation(summary = "Find account of candidate")
    @GetMapping("/by-email")
    public ResponseEntity<Candidate> viewAccount(@RequestParam String email) {
        return ResponseEntity.ok(service.viewAccount(email));
    }

    @Operation(summary = "Finds all applications of candidate")
    @GetMapping("/{id}")
    public ResponseEntity<List<CandidateApplication>> viewApplications(@PathVariable Long id) {
        return ResponseEntity.ok(service.viewApplications(id));
    }

    @Operation(summary = "Candidate applies to job on Job Post")
    @GetMapping
    public ResponseEntity<CandidateApplication> applyToJob(@RequestParam Long postId, @RequestParam Long candidateId, @RequestParam Long jobId) {
        return ResponseEntity.ok(service.applyToJob(postId, candidateId, jobId));
    }
}
