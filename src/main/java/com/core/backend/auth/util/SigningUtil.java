package com.core.backend.auth.util;

import java.security.Key;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class SigningUtil {
	public static Key getSigningKey(String secretKey) {
		byte[] decodeKey = Decoders.BASE64URL.decode(secretKey);
		return Keys.hmacShaKeyFor(decodeKey);
	}
}
