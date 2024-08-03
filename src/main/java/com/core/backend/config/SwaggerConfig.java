package com.core.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "스르륵 API 명세서",
                description = "<h3>핀테크 프로젝트 더치페이 플랫폼 구현 프로젝트</h3>",
                version = "v1"
        )
)
public class SwaggerConfig {

    String root = "com.core.backend";
    String[] paths = {
            root
    };

    @Bean
    public GroupedOpenApi getEntireApi() {
        return GroupedOpenApi.builder()
                .group("ENTIRE")
                .packagesToScan(paths)
                .build();
    }
}
