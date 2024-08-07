package com.core.backend.auth.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.core.backend.auth.exception.AuthException;
import com.core.backend.auth.property.TokenProperties;
import com.core.backend.common.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BearerTokenValidator {

	private final TokenProperties tokenProperties;

	public Long validateToken(String accessToken) {

		Claims claim = extractClaim(accessToken);
		return claim != null ? claim.get("id", Long.class) : null;
	}

	private Claims extractClaim(String accessToken) {

		try {
			return Jwts.parserBuilder()
				.setSigningKey(SigningUtil.getSigningKey(tokenProperties.getSecretKey()))
				.build()
				.parseClaimsJws(accessToken)
				.getBody();

		} catch (JwtException | IllegalArgumentException e) {
			handleJwtException(e);
			return null;
		}
	}

	private void handleJwtException(RuntimeException e) {
		Map<Class<? extends RuntimeException>, ErrorCode> exceptionMap = Map.of(
			DecodingException.class, ErrorCode.INVALID_SIGNATURE,
			WeakKeyException.class, ErrorCode.INVALID_SIGNATURE,
			SignatureException.class, ErrorCode.INVALID_SIGNATURE,
			NullPointerException.class, ErrorCode.INVALID_SIGNATURE,
			MalformedJwtException.class, ErrorCode.INVALID_TOKEN_FORMAT,
			ExpiredJwtException.class, ErrorCode.EXPIRED_TOKEN
		);

		ErrorCode authErrorCode = exceptionMap.getOrDefault(e.getClass(), ErrorCode.INTERNAL_SERVER_ERROR);

		throw new AuthException(authErrorCode, e);
	}

}
