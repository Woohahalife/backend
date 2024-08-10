package com.core.backend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.config.AuditingConfig;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class ServiceTestEnvSupport {
}
