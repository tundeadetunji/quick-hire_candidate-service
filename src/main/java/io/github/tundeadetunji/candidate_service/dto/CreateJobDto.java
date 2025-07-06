package io.github.tundeadetunji.candidate_service.dto;

import lombok.Data;

@Data
public class CreateJobDto {
    private Long recruiterId;
    private String title;
    private String mandatoryRequirement;
    private String desirableRequirement;
    private String minimumSalary;
    private String description;
}
