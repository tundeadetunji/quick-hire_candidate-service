package io.github.tundeadetunji.candidate_service.dto;

import io.github.tundeadetunji.candidate_service.constants.DtoMessages;
import io.github.tundeadetunji.candidate_service.domain.enums.Qualification;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CandidateDto {
    @NotBlank(message = DtoMessages.MESSAGE_FIRST_NAME_NOT_PROVIDED)
    private String firstName;
    private String middleName;
    @NotBlank(message = DtoMessages.MESSAGE_LAST_NAME_NOT_PROVIDED)
    private String lastName;
    @NotBlank(message = DtoMessages.MESSAGE_USERNAME_NOT_PROVIDED)
    private String username;
    @NotBlank(message = DtoMessages.MESSAGE_PASSWORD_NOT_PROVIDED)
    private String password;
    private Qualification highestQualification;
    private List<String> additionalQualifications;
    @NotBlank(message = DtoMessages.MESSAGE_RESUME_LINK_NOT_PROVIDED)
    private String resumeLink;
    @NotBlank(message = DtoMessages.MESSAGE_JOB_TITLE_NOT_PROVIDED)
    private String jobTitle;
    private List<String> phones;
    @NotBlank(message = DtoMessages.MESSAGE_EMAIL_NOT_PROVIDED)
    private String email;
}
