package com.core.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .tags(getTagList());
    }

    private List<Tag> getTagList() {
        return List.of(
                new Tag().name("group-controller").description("<b>[Group - 모임]</b> 정산 모임 관련  API"),
                new Tag().name("health-check-controller").description("<b>[Healthcheck]</b> 서버 healthcheck API"),
                new Tag().name("settlement-controller").description("<b>[Settlement - 정산]</b> 정산 관련 API")
        );
    }
}
