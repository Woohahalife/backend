package com.core.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.core.backend.auth.property.TokenProperties;

@SpringBootApplication
@EnableConfigurationProperties(TokenProperties.class)
public class FintechBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintechBackendApplication.class, args);
	}
}
