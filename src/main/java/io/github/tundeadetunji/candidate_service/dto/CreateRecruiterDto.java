package io.github.tundeadetunji.candidate_service.dto;

import io.github.tundeadetunji.candidate_service.constants.DtoMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CreateRecruiterDto {
    @NotBlank(message = DtoMessages.MESSAGE_FIRST_NAME_NOT_PROVIDED)
    private String firstName;
    private String middleName;
    @NotBlank(message = DtoMessages.MESSAGE_LAST_NAME_NOT_PROVIDED)
    private String lastName;
    @NotBlank(message = DtoMessages.MESSAGE_DEPARTMENT_NOT_PROVIDED)
    private String department;
    private List<String> phones;
    @NotBlank(message = DtoMessages.MESSAGE_EMAIL_NOT_PROVIDED)
    private String email;
}
