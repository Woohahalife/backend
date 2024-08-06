package com.core.backend.auth.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "access-token")
public class TokenProperties {

	private final String secretKey;

	private final long expireLength;

	public TokenProperties(String secretKey, long expireLength) {
		this.secretKey = secretKey;
		this.expireLength = expireLength;
	}
}
