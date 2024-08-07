package com.core.backend.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.user.infra.encryption.AesAlgEncryptor;

class UserTest {

	private PasswordEncryptor passwordEncryptor;

	@BeforeEach
	public void setUp() {
		passwordEncryptor = new AesAlgEncryptor();
	}

	@Test
	@DisplayName("비밀번호를 인코딩할 수 있다.")
	void passwordEncoding() {
		// given
		String rawPassword = "password123";
		User user = User.builder()
			.email("test@gmail.com")
			.password(rawPassword)
			.name("test1")
			.phoneNumber("01042421075")
			.build();

		// when
		user.encode(passwordEncryptor);

		// then
		assertThat(rawPassword).isNotEqualTo(user.getPassword().getPassword());
	}

	@Test
	@DisplayName("비밀번호를 디코딩할 수 있다.")
	void passwordDecoding() {
		// given
		String rawPassword = "password123";
		User user = User.builder()
			.email("test@gmail.com")
			.password(rawPassword)
			.name("test1")
			.phoneNumber("01042421075")
			.build();

		user.encode(passwordEncryptor);

		// when
		String decodedPassword = user.getPassword().decode(passwordEncryptor);

		// then
		assertThat(rawPassword).isEqualTo(decodedPassword);
	}

}
