package com.core.backend.user.infra.encryption;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.core.backend.user.domain.PasswordEncryptor;

@Component
public class AesAlgEncryptor implements PasswordEncryptor {

	private static final String alg = "AES/CBC/PKCS5Padding"; // AES 암호화 알고리즘 사용
	private final String key = "01234567890123456789012345678901";
	private final String iv = key.substring(0, 16); // 16byte

	@Override
	public String encrypt(String password) {

		try {
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

			byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String decrypt(String cipherText) {

		try {
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

			byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
			byte[] decrypted = cipher.doFinal(decodedBytes);
			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}
}
