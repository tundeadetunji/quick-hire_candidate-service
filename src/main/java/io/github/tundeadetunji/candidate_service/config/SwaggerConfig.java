package io.github.tundeadetunji.candidate_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI quickHireApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("QuickHire+ Candidate Service API")
                        .version("v1.0")
                        .description("APIs for managing candidates, applications, and notifications."));
    }

    /*@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("candidate")
                .pathsToMatch("/api/v1/candidate/**")
                .build();
    }*/
}
