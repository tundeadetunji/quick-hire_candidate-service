package io.github.tundeadetunji.candidate_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.candidate_service.domain.model.Candidate;
import io.github.tundeadetunji.candidate_service.dto.CandidateDto;
import io.github.tundeadetunji.candidate_service.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidateController.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService service;

    @Test
    void registerAccount_shouldReturnCreatedCandidate() throws Exception {
        CandidateDto dto = new CandidateDto();
        dto.setFirstName("Carla");
        dto.setLastName("Doe");
        dto.setUsername("carladoe");
        dto.setPassword("secure123");
        dto.setResumeLink("https://link.com/resume.pdf");
        dto.setJobTitle("Software Engineer");
        dto.setEmail("carla@gmail.com");
        dto.setPhones(List.of("123456789"));

        Candidate candidate = Candidate.from(dto);
        candidate.setId(100L);

        when(service.registerAccount(any())).thenReturn(candidate);

        mockMvc.perform(post("/api/v1/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.email").value("carla@gmail.com"));
    }
}
