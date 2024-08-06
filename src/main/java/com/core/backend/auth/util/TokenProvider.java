package com.core.backend.auth.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.core.backend.auth.property.TokenProperties;
import com.core.backend.user.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

/**
 * Jwt를 생성하는 클래스
 */
@Component
@RequiredArgsConstructor
public class TokenProvider {

	public String generateAccessToken(TokenProperties tokenProperties, User user) {
		Date issuedAt = new Date(System.currentTimeMillis());
		Date expiration = new Date(System.currentTimeMillis() + tokenProperties.getExpireLength());

		return Jwts.builder()
			.claim("id", user.getId())
			.setIssuedAt(issuedAt)
			.setExpiration(expiration)
			.signWith(SigningUtil.getSigningKey(tokenProperties.getSecretKey()), SignatureAlgorithm.HS256)
			.compact();
	}
}
