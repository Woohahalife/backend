package com.core.backend.common.initializer;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.core.backend.user.domain.PasswordEncryptor;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.user.infra.encryption.AesAlgEncryptor;

import jakarta.annotation.PostConstruct;

@Component
@Profile("test")
public class DataInitializer {

	private final UserRepository userRepository;
	private final PasswordEncryptor passwordEncryptor = new AesAlgEncryptor();

	public DataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void initialize() {
		// 데이터 초기화 로직
		User user1 = User.of("string1", "string", "string", "01011111111")
			.encode(passwordEncryptor);
		User user2 = User.of("string2", "string", "string", "01011111112")
			.encode(passwordEncryptor);
		User user3 = User.of("string3", "string", "string", "01011111113")
			.encode(passwordEncryptor);
		User user4 = User.of("string4", "string", "string", "01011111114")
			.encode(passwordEncryptor);
		User user5 = User.of("string5", "string", "string", "01011111115")
			.encode(passwordEncryptor);

		userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
	}
}
