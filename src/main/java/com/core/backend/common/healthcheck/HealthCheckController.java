package com.core.backend.common.healthcheck;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping("/health")
    @Operation(summary = "서버 health check용 api", description = "서버가 가동 중인지 체크한다.")
    public Map<String, Object> healthCheck() {
        Map<String, Object> status = new LinkedHashMap<>();
        status.put("status", "Connected");
        return status;
    }
}
