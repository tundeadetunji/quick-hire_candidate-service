package io.github.tundeadetunji.candidate_service.dto;

import lombok.Data;

@Data
public class UpdateJobDto {
    private String title;
    private String mandatoryRequirement;
    private String desirableRequirement;
    private String minimumSalary;
    private String description;
}
